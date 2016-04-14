package de.unikassel.ttc2016.model.util;

import org.sdmlib.models.pattern.PatternObject;
import de.unikassel.ttc2016.model.Method;
import org.sdmlib.models.pattern.AttributeConstraint;

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
   
}
