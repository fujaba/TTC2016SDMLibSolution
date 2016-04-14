package de.unikassel.ttc2016.model.util;

import org.sdmlib.models.pattern.PatternObject;
import de.unikassel.ttc2016.model.Method;
import org.sdmlib.models.pattern.AttributeConstraint;
import de.unikassel.ttc2016.model.util.ClassModelPO;
import de.unikassel.ttc2016.model.Feature;
import de.unikassel.ttc2016.model.ClassModel;
import de.unikassel.ttc2016.model.util.MethodPO;
import de.unikassel.ttc2016.model.util.ClassPO;
import de.unikassel.ttc2016.model.Class;
import de.unikassel.ttc2016.model.util.AttributePO;
import de.unikassel.ttc2016.model.Attribute;
import de.unikassel.ttc2016.model.util.AttributeSet;
import de.unikassel.ttc2016.model.util.MethodSet;

public class MethodPO extends PatternObject<MethodPO, Method>
{

    public MethodSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MethodSet matches = new MethodSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Method) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public MethodPO(){
      newInstance(null);
   }

   public MethodPO(Method... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
   public MethodPO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Method.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MethodPO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Method.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MethodPO createName(String value)
   {
      this.startCreate().filterName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Method) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public MethodPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Method) getCurrentMatch()).setName(value);
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

   public MethodPO filterClassmodel(ClassModelPO tgt)
   {
      return hasLinkConstraint(tgt, Feature.PROPERTY_CLASSMODEL);
   }

   public MethodPO createClassmodel(ClassModelPO tgt)
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

   public MethodPO filterIsEncapsulatedBy(ClassPO tgt)
   {
      return hasLinkConstraint(tgt, Feature.PROPERTY_ISENCAPSULATEDBY);
   }

   public MethodPO createIsEncapsulatedBy(ClassPO tgt)
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

   public AttributePO filterDataDependency()
   {
      AttributePO result = new AttributePO(new Attribute[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Method.PROPERTY_DATADEPENDENCY, result);
      
      return result;
   }

   public AttributePO createDataDependency()
   {
      return this.startCreate().filterDataDependency().endCreate();
   }

   public MethodPO filterDataDependency(AttributePO tgt)
   {
      return hasLinkConstraint(tgt, Method.PROPERTY_DATADEPENDENCY);
   }

   public MethodPO createDataDependency(AttributePO tgt)
   {
      return this.startCreate().filterDataDependency(tgt).endCreate();
   }

   public AttributeSet getDataDependency()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Method) this.getCurrentMatch()).getDataDependency();
      }
      return null;
   }

   public MethodPO filterMethod()
   {
      MethodPO result = new MethodPO(new Method[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Method.PROPERTY_METHOD, result);
      
      return result;
   }

   public MethodPO createMethod()
   {
      return this.startCreate().filterMethod().endCreate();
   }

   public MethodPO filterMethod(MethodPO tgt)
   {
      return hasLinkConstraint(tgt, Method.PROPERTY_METHOD);
   }

   public MethodPO createMethod(MethodPO tgt)
   {
      return this.startCreate().filterMethod(tgt).endCreate();
   }

   public Method getMethod()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Method) this.getCurrentMatch()).getMethod();
      }
      return null;
   }

   public MethodPO filterFunctionalDependency()
   {
      MethodPO result = new MethodPO(new Method[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Method.PROPERTY_FUNCTIONALDEPENDENCY, result);
      
      return result;
   }

   public MethodPO createFunctionalDependency()
   {
      return this.startCreate().filterFunctionalDependency().endCreate();
   }

   public MethodPO filterFunctionalDependency(MethodPO tgt)
   {
      return hasLinkConstraint(tgt, Method.PROPERTY_FUNCTIONALDEPENDENCY);
   }

   public MethodPO createFunctionalDependency(MethodPO tgt)
   {
      return this.startCreate().filterFunctionalDependency(tgt).endCreate();
   }

   public MethodSet getFunctionalDependency()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Method) this.getCurrentMatch()).getFunctionalDependency();
      }
      return null;
   }

}
