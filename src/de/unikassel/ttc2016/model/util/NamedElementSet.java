/*
   Copyright (c) 2017 zuendorf
   
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

import de.uniks.networkparser.list.SimpleSet;
import de.unikassel.ttc2016.model.NamedElement;
import de.uniks.networkparser.interfaces.Condition;
import de.unikassel.ttc2016.model.ClassModel;
import de.unikassel.ttc2016.model.util.ClassModelSet;
import de.unikassel.ttc2016.model.Class;
import de.unikassel.ttc2016.model.util.ClassSet;
import de.unikassel.ttc2016.model.Feature;
import de.unikassel.ttc2016.model.util.FeatureSet;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;

public class NamedElementSet extends SimpleSet<NamedElement>
{
	public java.lang.Class<?> getTypClass() {
		return NamedElement.class;
	}

   public NamedElementSet()
   {
      // empty
   }

   public NamedElementSet(NamedElement... objects)
   {
      for (NamedElement obj : objects)
      {
         this.add(obj);
      }
   }

   public NamedElementSet(Collection<NamedElement> objects)
   {
      this.addAll(objects);
   }

   public static final NamedElementSet EMPTY_SET = new NamedElementSet().withFlag(NamedElementSet.READONLY);


   public NamedElementPO createNamedElementPO()
   {
      return new NamedElementPO(this.toArray(new NamedElement[this.size()]));
   }


   public String getEntryType()
   {
      return "de.unikassel.ttc2016.model.NamedElement";
   }


   @Override
   public NamedElementSet getNewList(boolean keyValue)
   {
      return new NamedElementSet();
   }


   public NamedElementSet filter(Condition<NamedElement> condition) {
      NamedElementSet filterList = new NamedElementSet();
      filterItems(filterList, condition);
      return filterList;
   }

   public ClassModelSet instanceOfClassModel()
   {
      ClassModelSet result = new ClassModelSet();
      
      for(Object obj : this)
      {
         if (obj instanceof ClassModel)
         {
            result.with(obj);
         }
      }
      
      return result;
   }

   public ClassSet instanceOfClass()
   {
      ClassSet result = new ClassSet();
      
      for(Object obj : this)
      {
         if (obj instanceof Class)
         {
            result.with(obj);
         }
      }
      
      return result;
   }

   public FeatureSet instanceOfFeature()
   {
      FeatureSet result = new FeatureSet();
      
      for(Object obj : this)
      {
         if (obj instanceof Feature)
         {
            result.with(obj);
         }
      }
      
      return result;
   }

   @SuppressWarnings("unchecked")
   public NamedElementSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<NamedElement>)value);
      }
      else if (value != null)
      {
         this.add((NamedElement) value);
      }
      
      return this;
   }
   
   public NamedElementSet without(NamedElement value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of NamedElement objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public ObjectSet getName()
   {
      ObjectSet result = new ObjectSet();
      
      for (NamedElement obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of NamedElement objects and collect those NamedElement objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of NamedElement objects that match the parameter
    */
   public NamedElementSet createNameCondition(String value)
   {
      NamedElementSet result = new NamedElementSet();
      
      for (NamedElement obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of NamedElement objects and collect those NamedElement objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of NamedElement objects that match the parameter
    */
   public NamedElementSet createNameCondition(String lower, String upper)
   {
      NamedElementSet result = new NamedElementSet();
      
      for (NamedElement obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of NamedElement objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of NamedElement objects now with new attribute values.
    */
   public NamedElementSet withName(String value)
   {
      for (NamedElement obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

}
