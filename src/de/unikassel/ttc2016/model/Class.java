/*
   Copyright (c) 2016 lra
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package de.unikassel.ttc2016.model;

import de.unikassel.ttc2016.model.NamedElement;
import de.unikassel.ttc2016.model.ClassModel;
import de.unikassel.ttc2016.model.Attribute;
import de.unikassel.ttc2016.model.Method;
import de.unikassel.ttc2016.model.util.FeatureSet;
import de.unikassel.ttc2016.model.Feature;

public  class Class extends NamedElement
{

   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
   
      super.removeYou();

      setClassmodel(null);
      withoutEncapsulates(this.getEncapsulates().toArray(new Feature[this.getEncapsulates().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getName());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              many                       one
    * Class ----------------------------------- ClassModel
    *              classes                   classmodel
    * </pre>
    */
   
   public static final String PROPERTY_CLASSMODEL = "classmodel";

   private ClassModel classmodel = null;

   public ClassModel getClassmodel()
   {
      return this.classmodel;
   }

   public boolean setClassmodel(ClassModel value)
   {
      boolean changed = false;
      
      if (this.classmodel != value)
      {
         ClassModel oldValue = this.classmodel;
         
         if (this.classmodel != null)
         {
            this.classmodel = null;
            oldValue.withoutClasses(this);
         }
         
         this.classmodel = value;
         
         if (value != null)
         {
            value.withClasses(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CLASSMODEL, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Class withClassmodel(ClassModel value)
   {
      setClassmodel(value);
      return this;
   } 

   public ClassModel createClassmodel()
   {
      ClassModel value = new ClassModel();
      withClassmodel(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Class ----------------------------------- Feature
    *              isEncapsulatedBy                   encapsulates
    * </pre>
    */
   
   public static final String PROPERTY_ENCAPSULATES = "encapsulates";

   private FeatureSet encapsulates = null;
   
   public FeatureSet getEncapsulates()
   {
      if (this.encapsulates == null)
      {
         return FeatureSet.EMPTY_SET;
      }
   
      return this.encapsulates;
   }

   public Class withEncapsulates(Feature... value)
   {
      if(value==null){
         return this;
      }
      for (Feature item : value)
      {
         if (item != null)
         {
            if (this.encapsulates == null)
            {
               this.encapsulates = new FeatureSet();
            }
            
            boolean changed = this.encapsulates.add (item);

            if (changed)
            {
               item.withIsEncapsulatedBy(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_ENCAPSULATES, null, item);
            }
         }
      }
      return this;
   } 

   public Class withoutEncapsulates(Feature... value)
   {
      for (Feature item : value)
      {
         if ((this.encapsulates != null) && (item != null))
         {
            if (this.encapsulates.remove(item))
            {
               item.setIsEncapsulatedBy(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_ENCAPSULATES, item, null);
            }
         }
      }
      return this;
   }

   public Feature createEncapsulates()
   {
      Feature value = new Feature();
      withEncapsulates(value);
      return value;
   } 

   public Attribute createEncapsulatesAttribute()
   {
      Attribute value = new Attribute();
      withEncapsulates(value);
      return value;
   } 

   public Method createEncapsulatesMethod()
   {
      Method value = new Method();
      withEncapsulates(value);
      return value;
   } 
}
