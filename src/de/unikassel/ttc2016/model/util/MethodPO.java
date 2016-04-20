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

   public MethodPO filterUsedByMethods()
   {
      MethodPO result = new MethodPO(new Method[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Method.PROPERTY_USEDBYMETHODS, result);
      
      return result;
   }

   public MethodPO createUsedByMethods()
   {
      return this.startCreate().filterUsedByMethods().endCreate();
   }

   public MethodPO filterUsedByMethods(MethodPO tgt)
   {
      return hasLinkConstraint(tgt, Method.PROPERTY_USEDBYMETHODS);
   }

   public MethodPO createUsedByMethods(MethodPO tgt)
   {
      return this.startCreate().filterUsedByMethods(tgt).endCreate();
   }

   public MethodSet getUsedByMethods()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Method) this.getCurrentMatch()).getUsedByMethods();
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
