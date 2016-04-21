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

import de.unikassel.ttc2016.model.Feature;
import de.unikassel.ttc2016.model.ClassModel;
import de.unikassel.ttc2016.model.Class;
import de.unikassel.ttc2016.model.util.MethodSet;
import de.unikassel.ttc2016.model.Method;

public  class Attribute extends Feature
{

   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
   
      super.removeYou();

      setClassmodel(null);
      setIsEncapsulatedBy(null);
      withoutMethods(this.getMethods().toArray(new Method[this.getMethods().size()]));
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
    *              many                       many
    * Attribute ----------------------------------- Method
    *              dataDependency                   methods
    * </pre>
    */
   
   public static final String PROPERTY_METHODS = "methods";

   private MethodSet methods = null;
   
   public MethodSet getMethods()
   {
      if (this.methods == null)
      {
         return MethodSet.EMPTY_SET;
      }
   
      return this.methods;
   }

   public Attribute withMethods(Method... value)
   {
      if(value==null){
         return this;
      }
      for (Method item : value)
      {
         if (item != null)
         {
            if (this.methods == null)
            {
               this.methods = new MethodSet();
            }
            
            boolean changed = this.methods.add (item);

            if (changed)
            {
               item.withDataDependency(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_METHODS, null, item);
            }
         }
      }
      return this;
   } 

   public Attribute withoutMethods(Method... value)
   {
      for (Method item : value)
      {
         if ((this.methods != null) && (item != null))
         {
            if (this.methods.remove(item))
            {
               item.withoutDataDependency(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_METHODS, item, null);
            }
         }
      }
      return this;
   }

   public Method createMethods()
   {
      Method value = new Method();
      withMethods(value);
      return value;
   } 
}
