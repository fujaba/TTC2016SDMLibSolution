package de.unikassel.ttc2016.model.util;

import org.sdmlib.models.pattern.PatternObject;
import de.unikassel.ttc2016.model.Class;
import org.sdmlib.models.pattern.AttributeConstraint;
import de.unikassel.ttc2016.model.util.ClassModelPO;
import de.unikassel.ttc2016.model.ClassModel;
import de.unikassel.ttc2016.model.util.ClassPO;
import de.unikassel.ttc2016.model.util.FeaturePO;
import de.unikassel.ttc2016.model.Feature;
import de.unikassel.ttc2016.model.util.FeatureSet;

public class ClassPO extends PatternObject<ClassPO, Class>
{

    public ClassSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ClassSet matches = new ClassSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Class) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ClassPO(){
      newInstance(null);
   }

   public ClassPO(Class... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
   public ClassPO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Class.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ClassPO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Class.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ClassPO createName(String value)
   {
      this.startCreate().filterName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Class) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public ClassPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Class) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public ClassModelPO filterClassmodel()
   {
      ClassModelPO result = new ClassModelPO(new ClassModel[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Class.PROPERTY_CLASSMODEL, result);
      
      return result;
   }

   public ClassModelPO createClassmodel()
   {
      return this.startCreate().filterClassmodel().endCreate();
   }

   public ClassPO filterClassmodel(ClassModelPO tgt)
   {
      return hasLinkConstraint(tgt, Class.PROPERTY_CLASSMODEL);
   }

   public ClassPO createClassmodel(ClassModelPO tgt)
   {
      return this.startCreate().filterClassmodel(tgt).endCreate();
   }

   public ClassModel getClassmodel()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Class) this.getCurrentMatch()).getClassmodel();
      }
      return null;
   }

   public FeaturePO filterEncapsulates()
   {
      FeaturePO result = new FeaturePO(new Feature[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Class.PROPERTY_ENCAPSULATES, result);
      
      return result;
   }

   public FeaturePO createEncapsulates()
   {
      return this.startCreate().filterEncapsulates().endCreate();
   }

   public ClassPO filterEncapsulates(FeaturePO tgt)
   {
      return hasLinkConstraint(tgt, Class.PROPERTY_ENCAPSULATES);
   }

   public ClassPO createEncapsulates(FeaturePO tgt)
   {
      return this.startCreate().filterEncapsulates(tgt).endCreate();
   }

   public FeatureSet getEncapsulates()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Class) this.getCurrentMatch()).getEncapsulates();
      }
      return null;
   }

}
