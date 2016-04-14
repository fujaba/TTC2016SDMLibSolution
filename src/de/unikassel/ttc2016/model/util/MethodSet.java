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
   
package de.unikassel.ttc2016.model.util;

import org.sdmlib.models.modelsets.SDMSet;
import de.unikassel.ttc2016.model.Method;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.models.modelsets.StringList;

public class MethodSet extends SDMSet<Method>
{

   public static final MethodSet EMPTY_SET = new MethodSet().withFlag(MethodSet.READONLY);


   public MethodPO filterMethodPO()
   {
      return new MethodPO(this.toArray(new Method[this.size()]));
   }


   public String getEntryType()
   {
      return "de.unikassel.ttc2016.model.Method";
   }


   @SuppressWarnings("unchecked")
   public MethodSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Method>)value);
      }
      else if (value != null)
      {
         this.add((Method) value);
      }
      
      return this;
   }
   
   public MethodSet without(Method value)
   {
      this.remove(value);
      return this;
   }

   @Override
   public MethodSet filter(Condition<Method> newValue) {
      MethodSet filterList = new MethodSet();
      filterItems(filterList, newValue);
      return filterList;
   }

   /**
    * Loop through the current set of Method objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Method obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Method objects and collect those Method objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Method objects that match the parameter
    */
   public MethodSet filterName(String value)
   {
      MethodSet result = new MethodSet();
      
      for (Method obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Method objects and collect those Method objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Method objects that match the parameter
    */
   public MethodSet filterName(String lower, String upper)
   {
      MethodSet result = new MethodSet();
      
      for (Method obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Method objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Method objects now with new attribute values.
    */
   public MethodSet withName(String value)
   {
      for (Method obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

}
