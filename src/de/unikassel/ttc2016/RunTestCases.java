package de.unikassel.ttc2016.classmodel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.TreeMap;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.models.pattern.util.ReachabilityGraphCreator;
import org.sdmlib.serialization.xml.EmfIdMap;
import org.sdmlib.storyboards.StoryPage;

import de.unikassel.ttc2016.CRAIndexCalculator;
import de.unikassel.ttc2016.Metric;
import de.unikassel.ttc2016.model.Class;
import de.unikassel.ttc2016.model.ClassModel;
import de.unikassel.ttc2016.model.Feature;
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

//		runner.runCase("input_models/TTC_InputRDG_A.xmi");
		runner.runCase("input_models/TTC_InputRDG_B.xmi");
//		runner.runCase("input_models/TTC_InputRDG_C.xmi");
//		runner.runCase("input_models/TTC_InputRDG_D.xmi");
//		runner.runCase("input_models/TTC_InputRDG_E.xmi");
	}

	/**
	 * 
	 * @see <a href='../../../../../doc/runCase.html'>runCase.html</a>
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

			System.out.println("This test case consists of " + model.getClasses().size() + " classes.");

			printMetrics(model);

			ClassModelPO classModelPO = expandReachabilityGraph(model);
			
			// reduceClassesWhileMetricSucks(model);

			HashMap<ReachableState, Double> treeMap = new HashMap<ReachableState, Double>();
			
			double best = Double.MIN_VALUE;
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
			
			System.out.println("No of graphs:" + reachabilityGraph.getStates().size());
			printMetrics((ClassModel) bestState.getGraphRoot());
			System.out.println("CRA Index:" + best);
         
			
			
			// reachabilityGraph.d
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		story.dumpHTML();
	}

	private ClassModelPO expandReachabilityGraph(ClassModel model)
   {
	   IdMap idMap = ClassModelCreator.createIdMap("s");
      idMap.with(ReachabilityGraphCreator.createIdMap("rg"));
      
      ReachableState startState = new ReachableState().withGraphRoot(model);
      reachabilityGraph = new ReachabilityGraph().withMasterMap(idMap)
            .withStates(startState).withTodo(startState);
      
      // merge rule
      // ClassModelPO classModelPO = mergeDataDependencyRule();
      
      // reachabilityGraph.addToRules(classModelPO.getPattern().withName("mergedata"));
      
      ClassModelPO rule2PO = mergeMethodDependencyRule();
      reachabilityGraph.addToRules(rule2PO.getPattern().withName("mergemethod"));
      
      reachabilityGraph.explore();
      
      return rule2PO;
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
      f3.allMatches();
      f3.endCreate();
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
      f3.allMatches();
      f3.endCreate();
      c2PO.destroy();
      return classModelPO;
   }

   private void printMetrics(ClassModel model) {
		double craValue = Metric.computeFitness(model);
		double craIndex = Metric.cRAIndex(model);
		double viennaIndex = CRAIndexCalculator.evaluateModel(model);

		if(craValue != Double.NaN ){
			System.out.println("Albert says: " + craValue + " while Lennert says: " + craIndex);
		}else{
			System.out.println("Current max: " + craValue);
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

	private void reduceClassesWhileMetricSucks(ClassModel model)
	{
		double current = Metric.cRAIndex(model);


		while(current < 3){
			reduceClasses(model);
			
			current = Metric.cRAIndex(model);
		}
	}

	private void reduceClasses(ClassModel model) {
		ClassSet visitedClasses = new ClassSet();

		for (Class ci : model.getClasses()) {
			visitedClasses.add(ci);
			for (Class cj : model.getClasses()){
				if(ci != cj && !visitedClasses.contains(cj)){
					//pair of classes
					double cohesion = Metric.calcRatio(ci, ci);
					double coupling = Metric.calcRatio(ci, cj);

					//TODO choose more wisely
					if(cohesion < coupling){
						Class joinedClass = model.createClasses();
						FeatureSet encapsulatesCi = ci.getEncapsulates();
						FeatureSet encapsulatesCj = cj.getEncapsulates();
						joinedClass.withEncapsulates(encapsulatesCi.toArray(new Feature[encapsulatesCi.size()]));
						joinedClass.withEncapsulates(encapsulatesCj.toArray(new Feature[encapsulatesCj.size()]));
						joinedClass.setName(ci.getName() + " JOINED " + cj.getName());
						System.out.println(joinedClass.getName());
						ci.removeYou();
						cj.removeYou();
					}
				}
			}
		}
	}
}
