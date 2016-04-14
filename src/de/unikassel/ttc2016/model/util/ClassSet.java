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
import de.unikassel.ttc2016.model.Class;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.ObjectSet;
import de.unikassel.ttc2016.model.util.ClassModelSet;
import de.unikassel.ttc2016.model.ClassModel;
import java.util.Collections;
import de.unikassel.ttc2016.model.util.FeatureSet;
import de.unikassel.ttc2016.model.Feature;

public class ClassSet extends SDMSet<Class>
{

   public static final ClassSet EMPTY_SET = new ClassSet().withFlag(ClassSet.READONLY);


   public ClassPO filterClassPO()
   {
      return new ClassPO(this.toArray(new Class[this.size()]));
   }


   public String getEntryType()
   {
      return "de.unikassel.ttc2016.model.Class";
   }


   @SuppressWarnings("unchecked")
   public ClassSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Class>)value);
      }
      else if (value != null)
      {
         this.add((Class) value);
      }
      
      return this;
   }
   
   public ClassSet without(Class value)
   {
      this.remove(value);
      return this;
   }

   @Override
   public ClassSet filter(Condition<Class> newValue) {
      ClassSet filterList = new ClassSet();
      filterItems(filterList, newValue);
      return filterList;
   }

   /**
    * Loop through the current set of Class objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Class obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Class objects and collect those Class objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Class objects that match the parameter
    */
   public ClassSet filterName(String value)
   {
      ClassSet result = new ClassSet();
      
      for (Class obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Class objects and collect those Class objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Class objects that match the parameter
    */
   public ClassSet filterName(String lower, String upper)
   {
      ClassSet result = new ClassSet();
      
      for (Class obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Class objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Class objects now with new attribute values.
    */
   public ClassSet withName(String value)
   {
      for (Class obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Class objects and collect a set of the ClassModel objects reached via classmodel. 
    * 
    * @return Set of ClassModel objects reachable via classmodel
    */
   public ClassModelSet getClassmodel()
   {
      ClassModelSet result = new ClassModelSet();
      
      for (Class obj : this)
      {
         result.with(obj.getClassmodel());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Class objects and collect all contained objects with reference classmodel pointing to the object passed as parameter. 
    * 
    * @param value The object required as classmodel neighbor of the collected results. 
    * 
    * @return Set of ClassModel objects referring to value via classmodel
    */
   public ClassSet filterClassmodel(Object value)
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
      
      ClassSet answer = new ClassSet();
      
      for (Class obj : this)
      {
         if (neighbors.contains(obj.getClassmodel()) || (neighbors.isEmpty() && obj.getClassmodel() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Class object passed as parameter to the Classmodel attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Classmodel attributes.
    */
   public ClassSet withClassmodel(ClassModel value)
   {
      for (Class obj : this)
      {
         obj.withClassmodel(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Class objects and collect a set of the Feature objects reached via encapsulates. 
    * 
    * @return Set of Feature objects reachable via encapsulates
    */
   public FeatureSet getEncapsulates()
   {
      FeatureSet result = new FeatureSet();
      
      for (Class obj : this)
      {
         result.with(obj.getEncapsulates());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Class objects and collect all contained objects with reference encapsulates pointing to the object passed as parameter. 
    * 
    * @param value The object required as encapsulates neighbor of the collected results. 
    * 
    * @return Set of Feature objects referring to value via encapsulates
    */
   public ClassSet filterEncapsulates(Object value)
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
      
      ClassSet answer = new ClassSet();
      
      for (Class obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getEncapsulates()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Class object passed as parameter to the Encapsulates attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Encapsulates attributes.
    */
   public ClassSet withEncapsulates(Feature value)
   {
      for (Class obj : this)
      {
         obj.withEncapsulates(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Class object passed as parameter from the Encapsulates attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public ClassSet withoutEncapsulates(Feature value)
   {
      for (Class obj : this)
      {
         obj.withoutEncapsulates(value);
      }
      
      return this;
   }

}
