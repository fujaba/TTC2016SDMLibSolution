package de.unikassel.ttc2016;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.sdmlib.storyboards.Storyboard;

import de.unikassel.ttc2016.model.Class;
import de.unikassel.ttc2016.model.ClassModel;
import de.unikassel.ttc2016.model.Feature;
import de.unikassel.ttc2016.model.util.ClassModelCreator;
import de.unikassel.ttc2016.model.util.ClassModelPO;
import de.unikassel.ttc2016.model.util.ClassPO;
import de.unikassel.ttc2016.model.util.ClassSet;
import de.unikassel.ttc2016.model.util.FeaturePO;
import de.unikassel.ttc2016.model.util.FeatureSet;
import de.uniks.networkparser.IdMap;

public class GraphTransformationExecutor {


		public static void main(String[] args)
		{
			GraphTransformationExecutor runner = new GraphTransformationExecutor();

//			runner.runCase("input_models/TTC_InputRDG_A.xmi");
			runner.runCase("input_models/TTC_InputRDG_B.xmi");
//			runner.runCase("input_models/TTC_InputRDG_C.xmi");
//			runner.runCase("input_models/TTC_InputRDG_D.xmi");
//			runner.runCase("input_models/TTC_InputRDG_E.xmi");
		}

		/**
		 * 
		 * @see <a href='../../../../../doc/runCase.html'>runCase.html</a>
		 */
		public void runCase(String caseFile)
		{
			Storyboard story = new Storyboard();
			IdMap map = ClassModelCreator.createIdMap("g");

			byte[] allBytes;
			try
			{
				allBytes = Files.readAllBytes(Paths.get(caseFile));

				String text = new String(allBytes);

				Object root = map.decode(text);

				story.addObjectDiagram(root);

				ClassModel model = (ClassModel) root;

				addInitialClasses(model);

				System.out.println("This test case consists of " + model.getClasses().size() + " classes.");

				printMetrics(model);

				reduceClassesWhileMetricSucks(model);

				printMetrics(model);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			story.dumpHTML();
		}

		private void printMetrics(ClassModel model) {
			double craValue = Metric.computeFitness(model);
			double craIndex = Metric.cRAIndex(model);

			if(craValue != craIndex){
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
			newClassPO.createCondition(c -> c.withName("Class4"+featurePO.getName()) != null);
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
