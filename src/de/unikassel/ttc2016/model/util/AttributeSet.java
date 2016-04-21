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
   
package de.unikassel.ttc2016.model.util;

import org.sdmlib.models.modelsets.SDMSet;
import de.unikassel.ttc2016.model.Attribute;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.ObjectSet;
import de.unikassel.ttc2016.model.util.ClassModelSet;
import de.unikassel.ttc2016.model.ClassModel;
import de.unikassel.ttc2016.model.util.ClassSet;
import de.unikassel.ttc2016.model.Class;
import java.util.Collections;
import de.unikassel.ttc2016.model.util.MethodSet;
import de.unikassel.ttc2016.model.Method;

public class AttributeSet extends SDMSet<Attribute>
{

   public static final AttributeSet EMPTY_SET = new AttributeSet().withFlag(AttributeSet.READONLY);


   public AttributePO filterAttributePO()
   {
      return new AttributePO(this.toArray(new Attribute[this.size()]));
   }


   public String getEntryType()
   {
      return "de.unikassel.ttc2016.model.Attribute";
   }


   @SuppressWarnings("unchecked")
   public AttributeSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Attribute>)value);
      }
      else if (value != null)
      {
         this.add((Attribute) value);
      }
      
      return this;
   }
   
   public AttributeSet without(Attribute value)
   {
      this.remove(value);
      return this;
   }

   @Override
   public AttributeSet filter(Condition<Attribute> newValue) {
      AttributeSet filterList = new AttributeSet();
      filterItems(filterList, newValue);
      return filterList;
   }

   /**
    * Loop through the current set of Attribute objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Attribute obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Attribute objects and collect those Attribute objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Attribute objects that match the parameter
    */
   public AttributeSet filterName(String value)
   {
      AttributeSet result = new AttributeSet();
      
      for (Attribute obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Attribute objects and collect those Attribute objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Attribute objects that match the parameter
    */
   public AttributeSet filterName(String lower, String upper)
   {
      AttributeSet result = new AttributeSet();
      
      for (Attribute obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Attribute objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Attribute objects now with new attribute values.
    */
   public AttributeSet withName(String value)
   {
      for (Attribute obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Attribute objects and collect a set of the ClassModel objects reached via classmodel. 
    * 
    * @return Set of ClassModel objects reachable via classmodel
    */
   public ClassModelSet getClassmodel()
   {
      ClassModelSet result = new ClassModelSet();
      
      for (Attribute obj : this)
      {
         result.with(obj.getClassmodel());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Attribute objects and collect all contained objects with reference classmodel pointing to the object passed as parameter. 
    * 
    * @param value The object required as classmodel neighbor of the collected results. 
    * 
    * @return Set of ClassModel objects referring to value via classmodel
    */
   public AttributeSet filterClassmodel(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      AttributeSet answer = new AttributeSet();
      
      for (Attribute obj : this)
      {
         if (neighbors.contains(obj.getClassmodel()) || (neighbors.isEmpty() && obj.getClassmodel() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Attribute object passed as parameter to the Classmodel attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Classmodel attributes.
    */
   public AttributeSet withClassmodel(ClassModel value)
   {
      for (Attribute obj : this)
      {
         obj.withClassmodel(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Attribute objects and collect a set of the Class objects reached via isEncapsulatedBy. 
    * 
    * @return Set of Class objects reachable via isEncapsulatedBy
    */
   public ClassSet getIsEncapsulatedBy()
   {
      ClassSet result = new ClassSet();
      
      for (Attribute obj : this)
      {
         result.with(obj.getIsEncapsulatedBy());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Attribute objects and collect all contained objects with reference isEncapsulatedBy pointing to the object passed as parameter. 
    * 
    * @param value The object required as isEncapsulatedBy neighbor of the collected results. 
    * 
    * @return Set of Class objects referring to value via isEncapsulatedBy
    */
   public AttributeSet filterIsEncapsulatedBy(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      AttributeSet answer = new AttributeSet();
      
      for (Attribute obj : this)
      {
         if (neighbors.contains(obj.getIsEncapsulatedBy()) || (neighbors.isEmpty() && obj.getIsEncapsulatedBy() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Attribute object passed as parameter to the IsEncapsulatedBy attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their IsEncapsulatedBy attributes.
    */
   public AttributeSet withIsEncapsulatedBy(Class value)
   {
      for (Attribute obj : this)
      {
         obj.withIsEncapsulatedBy(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Attribute objects and collect a set of the Method objects reached via methods. 
    * 
    * @return Set of Method objects reachable via methods
    */
   public MethodSet getMethods()
   {
      MethodSet result = new MethodSet();
      
      for (Attribute obj : this)
      {
         result.with(obj.getMethods());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Attribute objects and collect all contained objects with reference methods pointing to the object passed as parameter. 
    * 
    * @param value The object required as methods neighbor of the collected results. 
    * 
    * @return Set of Method objects referring to value via methods
    */
   public AttributeSet filterMethods(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      AttributeSet answer = new AttributeSet();
      
      for (Attribute obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getMethods()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Attribute object passed as parameter to the Methods attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Methods attributes.
    */
   public AttributeSet withMethods(Method value)
   {
      for (Attribute obj : this)
      {
         obj.withMethods(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Attribute object passed as parameter from the Methods attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public AttributeSet withoutMethods(Method value)
   {
      for (Attribute obj : this)
      {
         obj.withoutMethods(value);
      }
      
      return this;
   }

}
