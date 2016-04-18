package de.unikassel.ttc2016.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.serialization.xml.EmfIdMap;
import org.sdmlib.storyboards.StoryPage;

import at.ac.tuwien.big.momot.examples.modularization.cra.eval.CRAIndexCalculator;
import de.unikassel.ttc2016.Metric;
import de.unikassel.ttc2016.model.util.ClassModelCreator;
import de.unikassel.ttc2016.model.util.ClassModelPO;
import de.unikassel.ttc2016.model.util.ClassPO;
import de.unikassel.ttc2016.model.util.FeaturePO;
import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.xml.XMLEntity;

public class RunTestCases
{
	public static void main(String[] args)
	{
		RunTestCases runner = new RunTestCases();

		runner.runCase("input_models/TTC_InputRDG_A.xmi");

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

			System.out.println(model.getClasses().size());

			Metric metric = new Metric();

			double craValue = metric.computeFitness(model);
			double craIndex = metric.cRAIndex(model);

			if(craValue != craIndex){
				System.out.println("Albert says: " + craValue + " while Lennert says: " + craIndex);
			}else{
				System.out.println("Current max: " + craValue);
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		story.dumpHTML();
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
