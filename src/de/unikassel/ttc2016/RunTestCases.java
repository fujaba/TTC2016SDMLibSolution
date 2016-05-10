package de.unikassel.ttc2016;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.junit.Test;
import org.sdmlib.CGUtil;
import org.sdmlib.models.SDMLibIdMap;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.ReachabilityGraph.Searchmode;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.models.pattern.util.ReachabilityGraphCreator;
import org.sdmlib.models.pattern.util.ReachableStateSet;
import org.sdmlib.models.pattern.util.RuleApplicationSet;
import org.sdmlib.serialization.xml.EmfIdMap;
import org.sdmlib.storyboards.StoryPage;

import de.unikassel.ttc2016.model.Attribute;
import de.unikassel.ttc2016.model.Class;
import de.unikassel.ttc2016.model.ClassModel;
import de.unikassel.ttc2016.model.Method;
import de.unikassel.ttc2016.model.util.AttributePO;
import de.unikassel.ttc2016.model.util.ClassModelCreator;
import de.unikassel.ttc2016.model.util.ClassModelPO;
import de.unikassel.ttc2016.model.util.ClassPO;
import de.unikassel.ttc2016.model.util.FeaturePO;
import de.unikassel.ttc2016.model.util.MethodPO;
import de.uniks.networkparser.IdMap;

public class RunTestCases
{
   private ReachabilityGraph reachabilityGraph;
   private ReachableState startState;
   private int numEvaluatedStatesForDepthSearch;
   private ReachableStateSet evaluated;
   private ReachableState bestState;
   private double best;
   private static long exploreDepth = 2000;
   private static ArrayList<Searchmode> modes;

   public static void main(String[] args)
   {
      modes = new ArrayList<Searchmode>();

      if (args != null)
      {
         switch (args.length)
         {
         case 0:
            break;
         case 1:
            exploreDepth = Long.parseLong(args[0]);
            modes.addAll(Arrays.asList(Searchmode.values()));
            break;
         default:
            exploreDepth = Long.parseLong(args[0]);
            for (int i = 1; i < args.length; i++)
            {
               try
               {
                  modes.add(Searchmode.valueOf(args[i]));
               }
               catch (Exception e)
               {
                  System.out.println("Error parsing search mode " + args[i]);
                  e.printStackTrace();
               }
            }
            break;
         }
      }

      RunTestCases runner = new RunTestCases();

      long[] levels = new long[]
      { exploreDepth };

      if (exploreDepth == -1)
      {
         levels = new long[]
         { 100, 200, 500, 1000, 2000, 5000, 10000 };
      }

      for (long l : levels)
      {
         exploreDepth = l;

         runner.logTime("input_models/TTC_InputRDG_A.xmi");
         runner.logTime("input_models/TTC_InputRDG_B.xmi");
         runner.logTime("input_models/TTC_InputRDG_C.xmi");
         runner.logTime("input_models/TTC_InputRDG_D.xmi");
         runner.logTime("input_models/TTC_InputRDG_E.xmi");
      }

   }

   private void logTime(String caseFileName)
   {
      for (Searchmode m : modes)
      {
         logTime(caseFileName, m);
      }
   }

   private void logTime(String caseFileName, Searchmode mode)
   {
      System.out.println("################################################\n" + caseFileName + "\n"
         + "################################################");

      long currentTimeMillis = System.currentTimeMillis();

      runCase(caseFileName, mode);

      long runtime = System.currentTimeMillis() - currentTimeMillis;

      Path path = Paths.get("log.csv");

      String timeStamp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());
      timeStamp += ";" + System.getProperty("user.name");
      timeStamp += ";" + runtime;
      timeStamp += ";" + best;
      timeStamp += ";" + exploreDepth;
      timeStamp += ";" + mode;
      ClassModel graphRoot = (ClassModel) bestState.getGraphRoot();
      timeStamp += ";" + graphRoot.getClasses().size();
      timeStamp += ";" + caseFileName;

      try
      {
         timeStamp += "\n";

         Files.write(path, timeStamp.getBytes(), StandardOpenOption.APPEND);
      }
      catch (NoSuchFileException nsfe)
      {
         try
         {
            Files.write(path, timeStamp.getBytes(), StandardOpenOption.CREATE);
         }
         catch (IOException e)
         {
            e.printStackTrace();
         }
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }

   /**
    * 
    * @see <a href='../../../../doc/TTC_InputRDG_A.html'>TTC_InputRDG_A.html</a>
    * @see <a href='../../../../doc/TTC_InputRDG_B.html'>TTC_InputRDG_B.html</a>
    * @see <a href='../../../../doc/TTC_InputRDG_C.html'>TTC_InputRDG_C.html</a>
    * @see <a href='../../../../doc/TTC_InputRDG_D.html'>TTC_InputRDG_D.html</a>
    * @see <a href='../../../../doc/TTC_InputRDG_E.html'>TTC_InputRDG_E.html</a>
    */
   private void runCase(String caseFileName)
   {
      for (Searchmode m : Searchmode.values())
      {
         runCase(caseFileName, m);
      }
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
    * @see <a href='../../../../doc/TTC_InputRDG_A.html'>TTC_InputRDG_A.html</a>
    * @see <a href='../../../../doc/TTC_InputRDG_Small1.html'>
    *      TTC_InputRDG_Small1.html</a>
    * @see <a href='../../../../doc/TTC_InputRDG_B.html'>TTC_InputRDG_B.html</a>
    * @see <a href='../../../../doc/TTC_InputRDG_C.html'>TTC_InputRDG_C.html</a>
    * @see <a href='../../../../doc/TTC_InputRDG_D.html'>TTC_InputRDG_D.html</a>
    * @see <a href='../../../../doc/TTC_InputRDG_E.html'>TTC_InputRDG_E.html</a>
    */
   public void runCase(String caseFile, Searchmode mode)
   {

      StoryPage story = new StoryPage();
      int pos = caseFile.lastIndexOf('/');
      String storyName = caseFile.substring(pos + 1);
      storyName = CGUtil.packageName(storyName);
      story.getStoryboard().setName(storyName);

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
         System.out.println("Applying search mode " + mode);

         expandReachabilityGraph(model, mode);

         evaluateStates();

         System.out.println("Reachability graph has " + reachabilityGraph.getStates().size()
            + " reachable states and " + reachabilityGraph.getTodo().size() + " states still to be expanded");

         story.add("Expand only best state:");

         ObjectSet elems = new ObjectSet();

         ReachableState currentState = bestState;

         System.out.println("Best state is : " + bestState);
         ReachableStateSet newStates = new ReachableStateSet().with(bestState);

         // search backward
         while (!newStates.isEmpty())
         {
            // add newStates to elems
            elems.addAll(newStates);

            // compute previous states
            RuleApplicationSet ruleapplications = newStates.getResultOf();

            elems.addAll(ruleapplications);

            newStates = ruleapplications.getSrc();
         }

         story.addObjectDiagramOnlyWith(elems);

         // System.out.println("Only improving paths through rg gives cra: " +
         // searchBetterCraStatesOnly());
         // String dumpDiagram = reachabilityGraph.dumpDiagram("rgDiagram");

         story.add("Reachability Graph:");
         // story.add(dumpDiagram);

         // check if we did better previously
         Path path = Paths.get(caseFile.split("/")[1] + ".cra");
         double craFromFile = Double.MIN_VALUE;
         if (Files.isReadable(path))
         {
            craFromFile = Double.valueOf(new String(Files.readAllBytes(path)));
         }
         if (craFromFile < bestState.getMetricValue())
         {
            System.out.println("Found a better result, writing to file.");
            Files.write(path, String.valueOf(bestState.getMetricValue()).getBytes(), StandardOpenOption.CREATE);
         }
         else if (craFromFile == bestState.getMetricValue())
         {
            System.out.println("Result is even to current best result in file.");
         }
         else
         {
            System.out.println("CRA FOR BEST RESULT IS WORSE THAN PREVIOUSLY! (" + craFromFile + " vs "
               + bestState.getMetricValue() + ")");
         }
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

      story.dumpHTML();
   }

   // private double searchBetterCraStatesOnly()
   // {
   // numEvaluatedStatesForDepthSearch = 0;
   // evaluated = new ReachableStateSet();
   // double result = getBetterCraFromState(startState);
   // System.out.println(
   // "Had to search through " + numEvaluatedStatesForDepthSearch + " states to
   // follow all better cra paths.");
   // return result;
   // }
   //
   // private double getBetterCraFromState(ReachableState state)
   // {
   // if (evaluated.contains(state))
   // {
   // return Double.NEGATIVE_INFINITY;
   // }
   // else
   // {
   // evaluated.add(state);
   // }
   // numEvaluatedStatesForDepthSearch++;
   // double cra = cra(state);
   // double bestSubCra = Double.NEGATIVE_INFINITY;
   // for (ReachableState tgt : state.getRuleapplications().getTgt())
   // {
   // double tgtCra = cra(tgt);
   // if (tgtCra >= cra)
   // {
   // double deepTgtCra = getBetterCraFromState(tgt);
   // if (bestSubCra < deepTgtCra)
   // {
   // bestSubCra = deepTgtCra;
   // }
   // }
   // }
   // return Math.max(cra, bestSubCra);
   // }

   private double cra(ReachableState state)
   {
      return CRAIndexCalculator.calculateCRAIndex((ClassModel) state.getGraphRoot());
   }

   private void evaluateStates()
   {
      best = Double.NEGATIVE_INFINITY;
      bestState = null;

      best = reachabilityGraph.getStates().getMetricValue().max();

      bestState = reachabilityGraph.getStates().filterMetricValue(best).first();

      printMetrics((ClassModel) bestState.getGraphRoot());
   }

   private void expandReachabilityGraphAllModes(ClassModel model)
   {
      for (Searchmode m : Searchmode.values())
      {
         expandReachabilityGraph(model, m);
      }
   }

   private void expandReachabilityGraph(ClassModel model, Searchmode mode)
   {
      IdMap idMap = new SDMLibIdMap("s").with(ClassModelCreator.createIdMap("s"));
      idMap.with(ReachabilityGraphCreator.createIdMap("rg"));

      startState = new ReachableState().withGraphRoot(model);
      reachabilityGraph = new ReachabilityGraph().withMasterMap(idMap).withStates(startState).withTodo(startState);
      reachabilityGraph
         .setMetric(graphRootObject -> CRAIndexCalculator.calculateCRAIndex((ClassModel) graphRootObject));

      // merge rule
      ClassModelPO classModelPO = mergeDataDependencyRule();

      reachabilityGraph.addToRules(classModelPO.getPattern().withName("mergedata"));

      ClassModelPO rule2PO = mergeMethodDependencyRule();
      reachabilityGraph.addToRules(rule2PO.getPattern().withName("mergemethod"));

      reachabilityGraph.explore(exploreDepth, mode);
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

   private void printMetrics(ClassModel model)
   {
      double craValue = Metric.computeFitness(model);
      double craIndex = Metric.cRAIndex(model);
      double viennaIndex = CRAIndexCalculator.evaluateModel(model);

      if (!(craValue == craIndex && craIndex == viennaIndex))
      {
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
      newClassPO.filter(c -> c.withName("Class4" + featurePO.getName()) != null);
      featurePO.endCreate();
      featurePO.allMatches();

      return classModelPO;
   }

   /**
    * 
    * @see <a href='../../../../doc/Rules.html'>Rules.html</a>
    */
   @Test
   public void testRules() throws Exception
   {
      StoryPage story = new StoryPage();

      ClassModelPO addInitialClasses = addInitialClasses(null);

      story.addPattern(addInitialClasses, false);

      story.addPattern(mergeDataDependencyRule(), false);

      story.addPattern(mergeMethodDependencyRule(), false);

      story.dumpHTML();
   }
}
