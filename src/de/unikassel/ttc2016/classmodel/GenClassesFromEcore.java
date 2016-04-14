package de.unikassel.ttc2016.classmodel;

import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.StoryPage;
import org.sdmlib.tools.EMFTool;

public class GenClassesFromEcore
{
     /**
    * 
    * @see <a href='../../../../../doc/GenClassesFromEcore.html'>GenClassesFromEcore.html</a>
 */
   public static void main(String[] args)
   {
      StoryPage story = new StoryPage();
      
      EMFTool emfTool = new EMFTool();
      
      ClassModel model = emfTool.ecoreModelToClassModel("de.unikassel.ttc2016.model", "./input_models/architectureCRA.ecore");
      
      story.addClassDiagram(model);
      
      model.generate();
      
      story.dumpHTML();
   }
}
