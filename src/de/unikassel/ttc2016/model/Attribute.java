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
import de.unikassel.ttc2016.model.Method;
   /**
    * 
    * @see <a href='../../../../../src/de/unikassel/ttc2016/classmodel/GenClassesFromEcore.java'>GenClassesFromEcore.java</a>
 */
   public  class Attribute extends Feature
{

   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
   
      super.removeYou();

      setClassmodel(null);
      setIsEncapsulatedBy(null);
      setMethod(null);
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
    * Attribute ----------------------------------- Method
    *              dataDependency                   method
    * </pre>
    */
   
   public static final String PROPERTY_METHOD = "method";

   private Method method = null;

   public Method getMethod()
   {
      return this.method;
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
            oldValue.withoutDataDependency(this);
         }
         
         this.method = value;
         
         if (value != null)
         {
            value.withDataDependency(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_METHOD, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Attribute withMethod(Method value)
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
}
