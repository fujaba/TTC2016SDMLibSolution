package de.unikassel.ttc2016.model.util;

import org.sdmlib.models.pattern.PatternObject;
import de.unikassel.ttc2016.model.NamedElement;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;

public class NamedElementPO extends PatternObject<NamedElementPO, NamedElement>
{

    public NamedElementSet allMatches()
   {
      this.setDoAllMatches(true);
      
      NamedElementSet matches = new NamedElementSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((NamedElement) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public NamedElementPO(){
      newInstance(null);
   }

   public NamedElementPO(NamedElement... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public NamedElementPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public NamedElementPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(NamedElement.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public NamedElementPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(NamedElement.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public NamedElementPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(NamedElement.PROPERTY_NAME)
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
         return ((NamedElement) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public NamedElementPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((NamedElement) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
}
