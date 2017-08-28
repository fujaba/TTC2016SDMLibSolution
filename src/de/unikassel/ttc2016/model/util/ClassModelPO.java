package de.unikassel.ttc2016.model.util;

import org.sdmlib.models.pattern.PatternObject;
import de.unikassel.ttc2016.model.ClassModel;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
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

   public ClassModelPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public ClassModelPO createNameCondition(String value)
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
   
   public ClassModelPO createNameCondition(String lower, String upper)
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
   
   public ClassModelPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(ClassModel.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
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
   
   public ClassPO createClassesPO()
   {
      ClassPO result = new ClassPO(new Class[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ClassModel.PROPERTY_CLASSES, result);
      
      return result;
   }

   public ClassPO createClassesPO(String modifier)
   {
      ClassPO result = new ClassPO(new Class[]{});
      
      result.setModifier(modifier);
      super.hasLink(ClassModel.PROPERTY_CLASSES, result);
      
      return result;
   }

   public ClassModelPO createClassesLink(ClassPO tgt)
   {
      return hasLinkConstraint(tgt, ClassModel.PROPERTY_CLASSES);
   }

   public ClassModelPO createClassesLink(ClassPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, ClassModel.PROPERTY_CLASSES, modifier);
   }

   public ClassSet getClasses()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ClassModel) this.getCurrentMatch()).getClasses();
      }
      return null;
   }

   public FeaturePO createFeaturesPO()
   {
      FeaturePO result = new FeaturePO(new Feature[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ClassModel.PROPERTY_FEATURES, result);
      
      return result;
   }

   public FeaturePO createFeaturesPO(String modifier)
   {
      FeaturePO result = new FeaturePO(new Feature[]{});
      
      result.setModifier(modifier);
      super.hasLink(ClassModel.PROPERTY_FEATURES, result);
      
      return result;
   }

   public ClassModelPO createFeaturesLink(FeaturePO tgt)
   {
      return hasLinkConstraint(tgt, ClassModel.PROPERTY_FEATURES);
   }

   public ClassModelPO createFeaturesLink(FeaturePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, ClassModel.PROPERTY_FEATURES, modifier);
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
