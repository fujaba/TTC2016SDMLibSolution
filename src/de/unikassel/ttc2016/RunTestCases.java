package de.unikassel.ttc2016;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.TreeMap;

import org.junit.Test;
import org.sdmlib.models.SDMLibIdMap;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.models.pattern.util.ReachabilityGraphCreator;
import org.sdmlib.serialization.xml.EmfIdMap;
import org.sdmlib.storyboards.StoryPage;

import de.unikassel.ttc2016.model.Attribute;
import de.unikassel.ttc2016.model.Class;
import de.unikassel.ttc2016.model.ClassModel;
import de.unikassel.ttc2016.model.Feature;
import de.unikassel.ttc2016.model.Method;
import de.unikassel.ttc2016.model.NamedElement;
import de.unikassel.ttc2016.model.util.AttributePO;
import de.unikassel.ttc2016.model.util.ClassModelCreator;
import de.unikassel.ttc2016.model.util.ClassModelPO;
import de.unikassel.ttc2016.model.util.ClassPO;
import de.unikassel.ttc2016.model.util.ClassSet;
import de.unikassel.ttc2016.model.util.FeaturePO;
import de.unikassel.ttc2016.model.util.FeatureSet;
import de.unikassel.ttc2016.model.util.MethodPO;
import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.xml.XMLEntity;

public class RunTestCases
{
	private ReachabilityGraph reachabilityGraph;

   public static void main(String[] args)
	{
		RunTestCases runner = new RunTestCases();

//		runner.runCase("input_models/TTC_InputRDG_Small1.xmi");
    runner.runCase("input_models/TTC_InputRDG_Paper.xmi");
//    runner.runCase("input_models/TTC_InputRDG_A.xmi");
//      runner.runCase("input_models/TTC_InputRDG_B.xmi");
//		runner.runCase("input_models/TTC_InputRDG_C.xmi");
//		runner.runCase("input_models/TTC_InputRDG_D.xmi");
//		runner.runCase("input_models/TTC_InputRDG_E.xmi");
	}

   
     /**
    * 
    * @see <a href='../../../../doc/CRA4Paper.html'>CRA4Paper.html</a>
 */
   @Test
   public void testCRA4Paper() throws Exception
   {
      StoryPage story = new StoryPage();
      
      ClassModel m = new ClassModel();
      
      Class cart = (Class) m.createClasses().withName("Cart");
      
      Attribute items = (Attribute) cart.createEncapsulatesAttribute().withName("items");
      Method addItem = (Method) cart.createEncapsulatesMethod().withName("addItem");
      
      Method cartTotal = (Method) cart.createEncapsulatesMethod().withName("cartTotal");
      Method checkout = (Method) cart.createEncapsulatesMethod().withName("checkout");
      
      Class item = (Class) m.createClasses().withName("Item");
      
      Attribute name = (Attribute) item.createEncapsulatesAttribute().withName("name");
      Attribute price = (Attribute) item.createEncapsulatesAttribute().withName("price");
      Attribute quantity = (Attribute) item.createEncapsulatesAttribute().withName("quantity");

      Method itemTotal = (Method) item.createEncapsulatesMethod().withName("itemTotal");
      Method print = (Method) item.createEncapsulatesMethod().withName("print");
      
      
      addItem.withDataDependency(items, name);
      cartTotal.withDataDependency(items).withFunctionalDependency(itemTotal);
      checkout.withDataDependency(items).withFunctionalDependency(cartTotal);
      print.withDataDependency(name, price, quantity);
      itemTotal.withDataDependency(price, quantity);
      
      printMetrics(m);
      
      story.dumpHTML();
   }
   
	/**
	 * 
	 * @see <a href='../../../../../doc/runCase.html'>runCase.html</a>
	 * @see <a href='../../../../doc/runCase.html'>runCase.html</a>
 */
	public void runCase(String caseFile)
	{
		StoryPage story = new StoryPage();

		EmfIdMap idMap = (EmfIdMap) new EmfIdMap("g").with(ClassModelCreator.createIdMap("g"));

		byte[] allBytes;
		try
		{
			allBytes = Files.readAllBytes(Paths.get(caseFile));

			String text = new String(allBytes);

			Object root = idMap.decode(text);

			story.addObjectDiagram(root);

			ClassModel model = (ClassModel) root;

			addInitialClasses(model);

			System.out.println("This test case consists of " + model.getClasses().size() + " features.");

			String reachabiltiyGraphDump = expandReachabilityGraph(model);
			
			story.add(reachabiltiyGraphDump);;
			
			HashMap<ReachableState, Double> treeMap = new HashMap<ReachableState, Double>();
			
			double best = Double.NEGATIVE_INFINITY;
			ReachableState bestState = null;
			for (ReachableState state : reachabilityGraph.getStates())
			{
			   ClassModel stateroot = (ClassModel) state.getGraphRoot();
			   double index = CRAIndexCalculator.calculateCRAIndex(stateroot);
			   
			   if (index > best)
			   {
			      best = index;
			      bestState = state;
			   }
			   
			   treeMap.put(state, index);
			}

			story.addObjectDiagram(bestState.getGraphRoot());
			
			printMetrics((ClassModel) bestState.getGraphRoot());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		story.dumpHTML();
	}

	private String expandReachabilityGraph(ClassModel model)
   {
	   IdMap idMap = new SDMLibIdMap("s").with(ClassModelCreator.createIdMap("s"));
      idMap.with(ReachabilityGraphCreator.createIdMap("rg"));
      
      ReachableState startState = new ReachableState().withGraphRoot(model);
      reachabilityGraph = new ReachabilityGraph().withMasterMap(idMap)
            .withStates(startState).withTodo(startState);
      
      // merge rule
      ClassModelPO classModelPO = mergeDataDependencyRule();
      
      reachabilityGraph.addToRules(classModelPO.getPattern().withName("mergedata"));
      
      ClassModelPO rule2PO = mergeMethodDependencyRule();
      reachabilityGraph.addToRules(rule2PO.getPattern().withName("mergemethod"));
      
      reachabilityGraph.explore();
      
      // String dumpDiagram = reachabilityGraph.dumpDiagram("reachabilityGraph");
      
      return "Hello";
   }

   private ClassModelPO mergeDataDependencyRule()
   {
      ClassModelPO classModelPO = new ClassModelPO();
      ClassPO c1PO = classModelPO.filterClasses();
      FeaturePO f1PO = c1PO.filterEncapsulates();
      MethodPO m1PO = f1PO.instanceOf(new MethodPO());
      AttributePO a2PO = m1PO.filterDataDependency();
      ClassPO c2PO = a2PO.filterIsEncapsulatedBy();
      c2PO.hasMatchOtherThen(c1PO);
      c2PO.filterClassmodel(classModelPO);
      c2PO.getPattern().clone(reachabilityGraph);
      c2PO.startSubPattern();
      FeaturePO f3 = c2PO.filterEncapsulates();
      f3.startCreate();
      f3.filterIsEncapsulatedBy(c1PO);
      f3.doAllMatches();
      f3.endCreate();
      c2PO.endSubPattern();
      c2PO.destroy();
      return classModelPO;
   }
   
   private ClassModelPO mergeMethodDependencyRule()
   {
      ClassModelPO classModelPO = new ClassModelPO();
      ClassPO c1PO = classModelPO.filterClasses();
      FeaturePO f1PO = c1PO.filterEncapsulates();
      MethodPO m1PO = f1PO.instanceOf(new MethodPO());
      MethodPO m2PO = m1PO.filterFunctionalDependency();
      ClassPO c2PO = m2PO.filterIsEncapsulatedBy();
      c2PO.hasMatchOtherThen(c1PO);
      c2PO.filterClassmodel(classModelPO);
      c2PO.getPattern().clone(reachabilityGraph);
      c2PO.startSubPattern();
      FeaturePO f3 = c2PO.filterEncapsulates();
      f3.startCreate();
      f3.filterIsEncapsulatedBy(c1PO);
      f3.doAllMatches();
      f3.endCreate();
      c2PO.endSubPattern();
      c2PO.destroy();
      return classModelPO;
   }

   private void printMetrics(ClassModel model) {
		double craValue = Metric.computeFitness(model);
		double craIndex = Metric.cRAIndex(model);
		double viennaIndex = CRAIndexCalculator.evaluateModel(model);

		if(craValue == craIndex && craIndex == viennaIndex ){
			//everyone agrees
		}else{
			System.out.println("A: " + craValue + " L: " + craIndex + " V: " + viennaIndex);
		}
	}

	private ClassModelPO addInitialClasses(ClassModel model)
	{
		ClassModelPO classModelPO = new ClassModelPO(model);

		FeaturePO featurePO = classModelPO.filterFeatures();

		featurePO.startCreate();

		ClassPO newClassPO = classModelPO.filterClasses();
		newClassPO.filterEncapsulates(featurePO);
		newClassPO.filter(c -> c.withName("Class4"+featurePO.getName()) != null);
		featurePO.endCreate();
		featurePO.allMatches();

		return classModelPO;
	}
}
