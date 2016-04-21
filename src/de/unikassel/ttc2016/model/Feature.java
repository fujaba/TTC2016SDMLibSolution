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
import de.unikassel.ttc2016.model.Class;

public  class Feature extends NamedElement
{

   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
   
      super.removeYou();

      setClassmodel(null);
      setIsEncapsulatedBy(null);
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
    * Feature ----------------------------------- ClassModel
    *              features                   classmodel
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
            oldValue.withoutFeatures(this);
         }
         
         this.classmodel = value;
         
         if (value != null)
         {
            value.withFeatures(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CLASSMODEL, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Feature withClassmodel(ClassModel value)
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
    *              many                       one
    * Feature ----------------------------------- Class
    *              encapsulates                   isEncapsulatedBy
    * </pre>
    */
   
   public static final String PROPERTY_ISENCAPSULATEDBY = "isEncapsulatedBy";

   private Class isEncapsulatedBy = null;

   public Class getIsEncapsulatedBy()
   {
      return this.isEncapsulatedBy;
   }

   public boolean setIsEncapsulatedBy(Class value)
   {
      boolean changed = false;
      
      if (this.isEncapsulatedBy != value)
      {
         Class oldValue = this.isEncapsulatedBy;
         
         if (this.isEncapsulatedBy != null)
         {
            this.isEncapsulatedBy = null;
            oldValue.withoutEncapsulates(this);
         }
         
         this.isEncapsulatedBy = value;
         
         if (value != null)
         {
            value.withEncapsulates(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ISENCAPSULATEDBY, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Feature withIsEncapsulatedBy(Class value)
   {
      setIsEncapsulatedBy(value);
      return this;
   } 

   public Class createIsEncapsulatedBy()
   {
      Class value = new Class();
      withIsEncapsulatedBy(value);
      return value;
   } 
}
