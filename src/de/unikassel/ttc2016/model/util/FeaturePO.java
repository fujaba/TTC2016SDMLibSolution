package de.unikassel.ttc2016.model.util;

import org.sdmlib.models.pattern.PatternObject;
import de.unikassel.ttc2016.model.Feature;
import org.sdmlib.models.pattern.AttributeConstraint;

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
   
}
