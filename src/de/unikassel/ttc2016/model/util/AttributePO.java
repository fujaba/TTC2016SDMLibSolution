package de.unikassel.ttc2016.model.util;

import org.sdmlib.models.pattern.PatternObject;
import de.unikassel.ttc2016.model.Attribute;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import de.unikassel.ttc2016.model.util.ClassModelPO;
import de.unikassel.ttc2016.model.ClassModel;
import de.unikassel.ttc2016.model.util.AttributePO;
import de.unikassel.ttc2016.model.util.MethodPO;
import de.unikassel.ttc2016.model.Method;
import de.unikassel.ttc2016.model.util.MethodSet;
import de.unikassel.ttc2016.model.util.ClassPO;
import de.unikassel.ttc2016.model.Class;

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

   public AttributePO(String modifier)
   {
      this.setModifier(modifier);
   }
   public AttributePO createNameCondition(String value)
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
   
   public AttributePO createNameCondition(String lower, String upper)
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
   
   public AttributePO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Attribute.PROPERTY_NAME)
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
   
   public ClassModelPO createClassmodelPO()
   {
      ClassModelPO result = new ClassModelPO(new ClassModel[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Attribute.PROPERTY_CLASSMODEL, result);
      
      return result;
   }

   public ClassModelPO createClassmodelPO(String modifier)
   {
      ClassModelPO result = new ClassModelPO(new ClassModel[]{});
      
      result.setModifier(modifier);
      super.hasLink(Attribute.PROPERTY_CLASSMODEL, result);
      
      return result;
   }

   public AttributePO createClassmodelLink(ClassModelPO tgt)
   {
      return hasLinkConstraint(tgt, Attribute.PROPERTY_CLASSMODEL);
   }

   public AttributePO createClassmodelLink(ClassModelPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Attribute.PROPERTY_CLASSMODEL, modifier);
   }

   public ClassModel getClassmodel()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Attribute) this.getCurrentMatch()).getClassmodel();
      }
      return null;
   }

   public MethodPO createMethodsPO()
   {
      MethodPO result = new MethodPO(new Method[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Attribute.PROPERTY_METHODS, result);
      
      return result;
   }

   public MethodPO createMethodsPO(String modifier)
   {
      MethodPO result = new MethodPO(new Method[]{});
      
      result.setModifier(modifier);
      super.hasLink(Attribute.PROPERTY_METHODS, result);
      
      return result;
   }

   public AttributePO createMethodsLink(MethodPO tgt)
   {
      return hasLinkConstraint(tgt, Attribute.PROPERTY_METHODS);
   }

   public AttributePO createMethodsLink(MethodPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Attribute.PROPERTY_METHODS, modifier);
   }

   public MethodSet getMethods()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Attribute) this.getCurrentMatch()).getMethods();
      }
      return null;
   }

   public ClassPO createIsEncapsulatedByPO()
   {
      ClassPO result = new ClassPO(new Class[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Attribute.PROPERTY_ISENCAPSULATEDBY, result);
      
      return result;
   }

   public ClassPO createIsEncapsulatedByPO(String modifier)
   {
      ClassPO result = new ClassPO(new Class[]{});
      
      result.setModifier(modifier);
      super.hasLink(Attribute.PROPERTY_ISENCAPSULATEDBY, result);
      
      return result;
   }

   public AttributePO createIsEncapsulatedByLink(ClassPO tgt)
   {
      return hasLinkConstraint(tgt, Attribute.PROPERTY_ISENCAPSULATEDBY);
   }

   public AttributePO createIsEncapsulatedByLink(ClassPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Attribute.PROPERTY_ISENCAPSULATEDBY, modifier);
   }

   public Class getIsEncapsulatedBy()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Attribute) this.getCurrentMatch()).getIsEncapsulatedBy();
      }
      return null;
   }

}
