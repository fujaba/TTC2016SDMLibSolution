package de.unikassel.ttc2016.model.util;

import org.sdmlib.models.pattern.PatternObject;
import de.unikassel.ttc2016.model.Attribute;
import org.sdmlib.models.pattern.AttributeConstraint;
import de.unikassel.ttc2016.model.util.ClassModelPO;
import de.unikassel.ttc2016.model.Feature;
import de.unikassel.ttc2016.model.ClassModel;
import de.unikassel.ttc2016.model.util.AttributePO;
import de.unikassel.ttc2016.model.util.ClassPO;
import de.unikassel.ttc2016.model.Class;
import de.unikassel.ttc2016.model.util.MethodPO;
import de.unikassel.ttc2016.model.Method;

public class AttributePO extends PatternObject<AttributePO, Attribute>
{

    public AttributeSet allMatches()
   {
      this.setDoAllMatches(true);
      
      AttributeSet matches = new AttributeSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Attribute) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public AttributePO(){
      newInstance(null);
   }

   public AttributePO(Attribute... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
   public AttributePO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Attribute.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AttributePO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Attribute.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AttributePO createName(String value)
   {
      this.startCreate().filterName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Attribute) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public AttributePO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Attribute) getCurrentMatch()).setName(value);
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

   public AttributePO filterClassmodel(ClassModelPO tgt)
   {
      return hasLinkConstraint(tgt, Feature.PROPERTY_CLASSMODEL);
   }

   public AttributePO createClassmodel(ClassModelPO tgt)
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

   public AttributePO filterIsEncapsulatedBy(ClassPO tgt)
   {
      return hasLinkConstraint(tgt, Feature.PROPERTY_ISENCAPSULATEDBY);
   }

   public AttributePO createIsEncapsulatedBy(ClassPO tgt)
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

   public MethodPO filterMethod()
   {
      MethodPO result = new MethodPO(new Method[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Attribute.PROPERTY_METHOD, result);
      
      return result;
   }

   public MethodPO createMethod()
   {
      return this.startCreate().filterMethod().endCreate();
   }

   public AttributePO filterMethod(MethodPO tgt)
   {
      return hasLinkConstraint(tgt, Attribute.PROPERTY_METHOD);
   }

   public AttributePO createMethod(MethodPO tgt)
   {
      return this.startCreate().filterMethod(tgt).endCreate();
   }

   public Method getMethod()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Attribute) this.getCurrentMatch()).getMethod();
      }
      return null;
   }

}
