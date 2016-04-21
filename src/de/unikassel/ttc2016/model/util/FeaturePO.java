package de.unikassel.ttc2016.model.util;

import org.sdmlib.models.pattern.PatternObject;
import de.unikassel.ttc2016.model.Feature;
import org.sdmlib.models.pattern.AttributeConstraint;
import de.unikassel.ttc2016.model.util.ClassModelPO;
import de.unikassel.ttc2016.model.ClassModel;
import de.unikassel.ttc2016.model.util.FeaturePO;
import de.unikassel.ttc2016.model.util.ClassPO;
import de.unikassel.ttc2016.model.Class;

public class FeaturePO extends PatternObject<FeaturePO, Feature>
{

    public FeatureSet allMatches()
   {
      this.setDoAllMatches(true);
      
      FeatureSet matches = new FeatureSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Feature) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public FeaturePO(){
      newInstance(null);
   }

   public FeaturePO(Feature... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
   public FeaturePO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Feature.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public FeaturePO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Feature.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public FeaturePO createName(String value)
   {
      this.startCreate().filterName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Feature) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public FeaturePO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Feature) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public ClassModelPO filterClassmodel()
   {
      ClassModelPO result = new ClassModelPO(new ClassModel[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Feature.PROPERTY_CLASSMODEL, result);
      
      return result;
   }

   public ClassModelPO createClassmodel()
   {
      return this.startCreate().filterClassmodel().endCreate();
   }

   public FeaturePO filterClassmodel(ClassModelPO tgt)
   {
      return hasLinkConstraint(tgt, Feature.PROPERTY_CLASSMODEL);
   }

   public FeaturePO createClassmodel(ClassModelPO tgt)
   {
      return this.startCreate().filterClassmodel(tgt).endCreate();
   }

   public ClassModel getClassmodel()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Feature) this.getCurrentMatch()).getClassmodel();
      }
      return null;
   }

   public ClassPO filterIsEncapsulatedBy()
   {
      ClassPO result = new ClassPO(new Class[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Feature.PROPERTY_ISENCAPSULATEDBY, result);
      
      return result;
   }

   public ClassPO createIsEncapsulatedBy()
   {
      return this.startCreate().filterIsEncapsulatedBy().endCreate();
   }

   public FeaturePO filterIsEncapsulatedBy(ClassPO tgt)
   {
      return hasLinkConstraint(tgt, Feature.PROPERTY_ISENCAPSULATEDBY);
   }

   public FeaturePO createIsEncapsulatedBy(ClassPO tgt)
   {
      return this.startCreate().filterIsEncapsulatedBy(tgt).endCreate();
   }

   public Class getIsEncapsulatedBy()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Feature) this.getCurrentMatch()).getIsEncapsulatedBy();
      }
      return null;
   }

}
