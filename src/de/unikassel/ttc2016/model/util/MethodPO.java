package de.unikassel.ttc2016.model.util;

import org.sdmlib.models.pattern.PatternObject;
import de.unikassel.ttc2016.model.Method;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import de.unikassel.ttc2016.model.util.ClassModelPO;
import de.unikassel.ttc2016.model.ClassModel;
import de.unikassel.ttc2016.model.util.MethodPO;
import de.unikassel.ttc2016.model.util.AttributePO;
import de.unikassel.ttc2016.model.Attribute;
import de.unikassel.ttc2016.model.util.AttributeSet;
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

   public MethodPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public MethodPO createNameCondition(String value)
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
   
   public MethodPO createNameCondition(String lower, String upper)
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
   
   public MethodPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Method.PROPERTY_NAME)
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
   
   public ClassModelPO createClassmodelPO()
   {
      ClassModelPO result = new ClassModelPO(new ClassModel[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Method.PROPERTY_CLASSMODEL, result);
      
      return result;
   }

   public ClassModelPO createClassmodelPO(String modifier)
   {
      ClassModelPO result = new ClassModelPO(new ClassModel[]{});
      
      result.setModifier(modifier);
      super.hasLink(Method.PROPERTY_CLASSMODEL, result);
      
      return result;
   }

   public MethodPO createClassmodelLink(ClassModelPO tgt)
   {
      return hasLinkConstraint(tgt, Method.PROPERTY_CLASSMODEL);
   }

   public MethodPO createClassmodelLink(ClassModelPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Method.PROPERTY_CLASSMODEL, modifier);
   }

   public ClassModel getClassmodel()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Method) this.getCurrentMatch()).getClassmodel();
      }
      return null;
   }

   public AttributePO createDataDependencyPO()
   {
      AttributePO result = new AttributePO(new Attribute[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Method.PROPERTY_DATADEPENDENCY, result);
      
      return result;
   }

   public AttributePO createDataDependencyPO(String modifier)
   {
      AttributePO result = new AttributePO(new Attribute[]{});
      
      result.setModifier(modifier);
      super.hasLink(Method.PROPERTY_DATADEPENDENCY, result);
      
      return result;
   }

   public MethodPO createDataDependencyLink(AttributePO tgt)
   {
      return hasLinkConstraint(tgt, Method.PROPERTY_DATADEPENDENCY);
   }

   public MethodPO createDataDependencyLink(AttributePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Method.PROPERTY_DATADEPENDENCY, modifier);
   }

   public AttributeSet getDataDependency()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Method) this.getCurrentMatch()).getDataDependency();
      }
      return null;
   }

   public ClassPO createIsEncapsulatedByPO()
   {
      ClassPO result = new ClassPO(new Class[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Method.PROPERTY_ISENCAPSULATEDBY, result);
      
      return result;
   }

   public ClassPO createIsEncapsulatedByPO(String modifier)
   {
      ClassPO result = new ClassPO(new Class[]{});
      
      result.setModifier(modifier);
      super.hasLink(Method.PROPERTY_ISENCAPSULATEDBY, result);
      
      return result;
   }

   public MethodPO createIsEncapsulatedByLink(ClassPO tgt)
   {
      return hasLinkConstraint(tgt, Method.PROPERTY_ISENCAPSULATEDBY);
   }

   public MethodPO createIsEncapsulatedByLink(ClassPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Method.PROPERTY_ISENCAPSULATEDBY, modifier);
   }

   public Class getIsEncapsulatedBy()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Method) this.getCurrentMatch()).getIsEncapsulatedBy();
      }
      return null;
   }

   public MethodPO createUsedByMethodsPO()
   {
      MethodPO result = new MethodPO(new Method[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Method.PROPERTY_USEDBYMETHODS, result);
      
      return result;
   }

   public MethodPO createUsedByMethodsPO(String modifier)
   {
      MethodPO result = new MethodPO(new Method[]{});
      
      result.setModifier(modifier);
      super.hasLink(Method.PROPERTY_USEDBYMETHODS, result);
      
      return result;
   }

   public MethodPO createUsedByMethodsLink(MethodPO tgt)
   {
      return hasLinkConstraint(tgt, Method.PROPERTY_USEDBYMETHODS);
   }

   public MethodPO createUsedByMethodsLink(MethodPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Method.PROPERTY_USEDBYMETHODS, modifier);
   }

   public MethodSet getUsedByMethods()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Method) this.getCurrentMatch()).getUsedByMethods();
      }
      return null;
   }

   public MethodPO createFunctionalDependencyPO()
   {
      MethodPO result = new MethodPO(new Method[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Method.PROPERTY_FUNCTIONALDEPENDENCY, result);
      
      return result;
   }

   public MethodPO createFunctionalDependencyPO(String modifier)
   {
      MethodPO result = new MethodPO(new Method[]{});
      
      result.setModifier(modifier);
      super.hasLink(Method.PROPERTY_FUNCTIONALDEPENDENCY, result);
      
      return result;
   }

   public MethodPO createFunctionalDependencyLink(MethodPO tgt)
   {
      return hasLinkConstraint(tgt, Method.PROPERTY_FUNCTIONALDEPENDENCY);
   }

   public MethodPO createFunctionalDependencyLink(MethodPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Method.PROPERTY_FUNCTIONALDEPENDENCY, modifier);
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
