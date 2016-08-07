package de.unikassel.ttc2016.classmodel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.graph.Attribute;
import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;



public class GenClassesFromEcore
{
     /**
    * 
    * @throws IOException 
     * @see <a href='../../../../../doc/GenClassesFromEcore.html'>GenClassesFromEcore.html</a>
 */
   public static void main(String[] args) throws IOException
   {
      Storyboard story = new Storyboard();
      
      IdMap map = new IdMap();
      String content = new String(Files.readAllBytes(new File("./input_models/architectureCRA.ecore").toPath()));
      ClassModel ecoreModel = new ClassModel("de.unikassel.ttc2016.model");
//      GraphList ecoreModel = (Cla) map.decodeEMF(content);
      map.decodeEMF(content, ecoreModel);
      
      
      story.addStep("Derive Class Model from ECORE");
      story.addClassDiagram(ecoreModel);
      
      ClassModel model = new ClassModel("de.unikassel.ttc2016.model");

      Clazz namedElementClass = model.createClazz("NamedElement")
      .with(new Attribute("name", DataType.create("String")) );

      Clazz classModelClass = model.createClazz("ClassModel")
      .withSuperClazz(namedElementClass);

      Clazz classClass = model.createClazz("Class")
      .withSuperClazz(namedElementClass);

      classModelClass.withBidirectional(classClass, "classes", Cardinality.MANY, "classmodel", Cardinality.ONE);

      Clazz featureClass = model.createClazz("Feature")
      .withSuperClazz(namedElementClass);

      featureClass.withBidirectional(classModelClass, "classmodel", Cardinality.ONE, "features", Cardinality.MANY);

      classClass.withBidirectional(featureClass, "encapsulates", Cardinality.MANY, "isEncapsulatedBy", Cardinality.ONE);

      Clazz attributeClass = model.createClazz("Attribute")
      .withSuperClazz(featureClass);

      Clazz methodClass = model.createClazz("Method")
      .withSuperClazz(featureClass);

      methodClass.withBidirectional(attributeClass, "dataDependency", Cardinality.MANY, "methods", Cardinality.MANY);

      methodClass.withBidirectional(methodClass, "functionalDependency", Cardinality.MANY, "usedByMethods", Cardinality.MANY);
     
      // model.getGenerator().insertModelCreationCodeHere("src");
      
      story.addStep("Enhance class model manually:");
      story.addClassDiagram(model);

      model.removeAllGeneratedCode();
      model.generate();
      
      
      story.dumpHTML();
   }
}
