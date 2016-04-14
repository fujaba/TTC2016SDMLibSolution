/*
   Copyright (c) 2016 zuendorf
   
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

import de.unikassel.ttc2016.model.Feature;
import de.unikassel.ttc2016.model.ClassModel;
import de.unikassel.ttc2016.model.Class;
import de.unikassel.ttc2016.model.util.AttributeSet;
import de.unikassel.ttc2016.model.Attribute;
import de.unikassel.ttc2016.model.util.MethodSet;
   /**
    * 
    * @see <a href='../../../../../src/de/unikassel/ttc2016/classmodel/GenClassesFromEcore.java'>GenClassesFromEcore.java</a>
 */
   public  class Method extends Feature
{

   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
   
      super.removeYou();

      setClassmodel(null);
      setIsEncapsulatedBy(null);
      withoutDataDependency(this.getDataDependency().toArray(new Attribute[this.getDataDependency().size()]));
      setMethod(null);
      withoutFunctionalDependency(this.getFunctionalDependency().toArray(new Method[this.getFunctionalDependency().size()]));
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
    *              one                       many
    * Method ----------------------------------- Attribute
    *              method                   dataDependency
    * </pre>
    */
   
   public static final String PROPERTY_DATADEPENDENCY = "dataDependency";

   private AttributeSet dataDependency = null;
   
   public AttributeSet getDataDependency()
   {
      if (this.dataDependency == null)
      {
         return AttributeSet.EMPTY_SET;
      }
   
      return this.dataDependency;
   }

   public Method withDataDependency(Attribute... value)
   {
      if(value==null){
         return this;
      }
      for (Attribute item : value)
      {
         if (item != null)
         {
            if (this.dataDependency == null)
            {
               this.dataDependency = new AttributeSet();
            }
            
            boolean changed = this.dataDependency.add (item);

            if (changed)
            {
               item.withMethod(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_DATADEPENDENCY, null, item);
            }
         }
      }
      return this;
   } 

   public Method withoutDataDependency(Attribute... value)
   {
      for (Attribute item : value)
      {
         if ((this.dataDependency != null) && (item != null))
         {
            if (this.dataDependency.remove(item))
            {
               item.setMethod(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_DATADEPENDENCY, item, null);
            }
         }
      }
      return this;
   }

   public Attribute createDataDependency()
   {
      Attribute value = new Attribute();
      withDataDependency(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Method ----------------------------------- Method
    *              functionalDependency                   method
    * </pre>
    */
   
   public static final String PROPERTY_METHOD = "method";

   private Method method = null;

   public Method getMethod()
   {
      return this.method;
   }
   public MethodSet getMethodTransitive()
   {
      MethodSet result = new MethodSet().with(this);
      return result.getMethodTransitive();
   }


   public boolean setMethod(Method value)
   {
      boolean changed = false;
      
      if (this.method != value)
      {
         Method oldValue = this.method;
         
         if (this.method != null)
         {
            this.method = null;
            oldValue.withoutFunctionalDependency(this);
         }
         
         this.method = value;
         
         if (value != null)
         {
            value.withFunctionalDependency(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_METHOD, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Method withMethod(Method value)
   {
      setMethod(value);
      return this;
   } 

   public Method createMethod()
   {
      Method value = new Method();
      withMethod(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * Method ----------------------------------- Method
    *              method                   functionalDependency
    * </pre>
    */
   
   public static final String PROPERTY_FUNCTIONALDEPENDENCY = "functionalDependency";

   private MethodSet functionalDependency = null;
   
   public MethodSet getFunctionalDependency()
   {
      if (this.functionalDependency == null)
      {
         return MethodSet.EMPTY_SET;
      }
   
      return this.functionalDependency;
   }
   public MethodSet getFunctionalDependencyTransitive()
   {
      MethodSet result = new MethodSet().with(this);
      return result.getFunctionalDependencyTransitive();
   }


   public Method withFunctionalDependency(Method... value)
   {
      if(value==null){
         return this;
      }
      for (Method item : value)
      {
         if (item != null)
         {
            if (this.functionalDependency == null)
            {
               this.functionalDependency = new MethodSet();
            }
            
            boolean changed = this.functionalDependency.add (item);

            if (changed)
            {
               item.withMethod(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_FUNCTIONALDEPENDENCY, null, item);
            }
         }
      }
      return this;
   } 

   public Method withoutFunctionalDependency(Method... value)
   {
      for (Method item : value)
      {
         if ((this.functionalDependency != null) && (item != null))
         {
            if (this.functionalDependency.remove(item))
            {
               item.withoutFunctionalDependency(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_FUNCTIONALDEPENDENCY, item, null);
            }
         }
      }
      return this;
   }

   public Method createFunctionalDependency()
   {
      Method value = new Method();
      withFunctionalDependency(value);
      return value;
   } 
}
