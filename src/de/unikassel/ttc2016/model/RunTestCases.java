package de.unikassel.ttc2016.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.sdmlib.serialization.xml.EmfIdMap;
import org.sdmlib.storyboards.StoryPage;

import de.unikassel.ttc2016.model.util.ClassModelCreator;
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
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      story.dumpHTML();
   }
}
