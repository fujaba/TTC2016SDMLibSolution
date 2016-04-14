package de.unikassel.ttc2016.model.util;

import org.sdmlib.models.pattern.PatternObject;
import de.unikassel.ttc2016.model.ClassModel;
import org.sdmlib.models.pattern.AttributeConstraint;
import de.unikassel.ttc2016.model.util.ClassPO;
import de.unikassel.ttc2016.model.Class;
import de.unikassel.ttc2016.model.util.ClassModelPO;
import de.unikassel.ttc2016.model.util.ClassSet;
import de.unikassel.ttc2016.model.util.FeaturePO;
import de.unikassel.ttc2016.model.Feature;
import de.unikassel.ttc2016.model.util.FeatureSet;

public class ClassModelPO extends PatternObject<ClassModelPO, ClassModel>
{

    public ClassModelSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ClassModelSet matches = new ClassModelSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ClassModel) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ClassModelPO(){
      newInstance(null);
   }

   public ClassModelPO(ClassModel... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
   public ClassModelPO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(ClassModel.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ClassModelPO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ClassModel.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ClassModelPO createName(String value)
   {
      this.startCreate().filterName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ClassModel) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public ClassModelPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ClassModel) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public ClassPO filterClasses()
   {
      ClassPO result = new ClassPO(new Class[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ClassModel.PROPERTY_CLASSES, result);
      
      return result;
   }

   public ClassPO createClasses()
   {
      return this.startCreate().filterClasses().endCreate();
   }

   public ClassModelPO filterClasses(ClassPO tgt)
   {
      return hasLinkConstraint(tgt, ClassModel.PROPERTY_CLASSES);
   }

   public ClassModelPO createClasses(ClassPO tgt)
   {
      return this.startCreate().filterClasses(tgt).endCreate();
   }

   public ClassSet getClasses()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ClassModel) this.getCurrentMatch()).getClasses();
      }
      return null;
   }

   public FeaturePO filterFeatures()
   {
      FeaturePO result = new FeaturePO(new Feature[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ClassModel.PROPERTY_FEATURES, result);
      
      return result;
   }

   public FeaturePO createFeatures()
   {
      return this.startCreate().filterFeatures().endCreate();
   }

   public ClassModelPO filterFeatures(FeaturePO tgt)
   {
      return hasLinkConstraint(tgt, ClassModel.PROPERTY_FEATURES);
   }

   public ClassModelPO createFeatures(FeaturePO tgt)
   {
      return this.startCreate().filterFeatures(tgt).endCreate();
   }

   public FeatureSet getFeatures()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ClassModel) this.getCurrentMatch()).getFeatures();
      }
      return null;
   }

}
