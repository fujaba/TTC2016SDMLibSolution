package de.unikassel.ttc2016.model.util;

import org.sdmlib.models.pattern.PatternObject;
import de.unikassel.ttc2016.model.Class;
import org.sdmlib.models.pattern.AttributeConstraint;

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
   
}
