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
import de.unikassel.ttc2016.model.ClassModel;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.ObjectSet;
import java.util.Collections;
import de.unikassel.ttc2016.model.util.ClassSet;
import de.unikassel.ttc2016.model.Class;
import de.unikassel.ttc2016.model.util.FeatureSet;
import de.unikassel.ttc2016.model.Feature;

public class ClassModelSet extends SDMSet<ClassModel>
{

   public static final ClassModelSet EMPTY_SET = new ClassModelSet().withFlag(ClassModelSet.READONLY);


   public ClassModelPO filterClassModelPO()
   {
      return new ClassModelPO(this.toArray(new ClassModel[this.size()]));
   }


   public String getEntryType()
   {
      return "de.unikassel.ttc2016.model.ClassModel";
   }


   @SuppressWarnings("unchecked")
   public ClassModelSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ClassModel>)value);
      }
      else if (value != null)
      {
         this.add((ClassModel) value);
      }
      
      return this;
   }
   
   public ClassModelSet without(ClassModel value)
   {
      this.remove(value);
      return this;
   }

   @Override
   public ClassModelSet filter(Condition<ClassModel> newValue) {
      ClassModelSet filterList = new ClassModelSet();
      filterItems(filterList, newValue);
      return filterList;
   }

   /**
    * Loop through the current set of ClassModel objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (ClassModel obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of ClassModel objects and collect those ClassModel objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ClassModel objects that match the parameter
    */
   public ClassModelSet filterName(String value)
   {
      ClassModelSet result = new ClassModelSet();
      
      for (ClassModel obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ClassModel objects and collect those ClassModel objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ClassModel objects that match the parameter
    */
   public ClassModelSet filterName(String lower, String upper)
   {
      ClassModelSet result = new ClassModelSet();
      
      for (ClassModel obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ClassModel objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of ClassModel objects now with new attribute values.
    */
   public ClassModelSet withName(String value)
   {
      for (ClassModel obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of ClassModel objects and collect a set of the Class objects reached via classes. 
    * 
    * @return Set of Class objects reachable via classes
    */
   public ClassSet getClasses()
   {
      ClassSet result = new ClassSet();
      
      for (ClassModel obj : this)
      {
         result.with(obj.getClasses());
      }
      
      return result;
   }

   /**
    * Loop through the current set of ClassModel objects and collect all contained objects with reference classes pointing to the object passed as parameter. 
    * 
    * @param value The object required as classes neighbor of the collected results. 
    * 
    * @return Set of Class objects referring to value via classes
    */
   public ClassModelSet filterClasses(Object value)
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
      
      ClassModelSet answer = new ClassModelSet();
      
      for (ClassModel obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getClasses()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the ClassModel object passed as parameter to the Classes attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Classes attributes.
    */
   public ClassModelSet withClasses(Class value)
   {
      for (ClassModel obj : this)
      {
         obj.withClasses(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the ClassModel object passed as parameter from the Classes attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public ClassModelSet withoutClasses(Class value)
   {
      for (ClassModel obj : this)
      {
         obj.withoutClasses(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of ClassModel objects and collect a set of the Feature objects reached via features. 
    * 
    * @return Set of Feature objects reachable via features
    */
   public FeatureSet getFeatures()
   {
      FeatureSet result = new FeatureSet();
      
      for (ClassModel obj : this)
      {
         result.with(obj.getFeatures());
      }
      
      return result;
   }

   /**
    * Loop through the current set of ClassModel objects and collect all contained objects with reference features pointing to the object passed as parameter. 
    * 
    * @param value The object required as features neighbor of the collected results. 
    * 
    * @return Set of Feature objects referring to value via features
    */
   public ClassModelSet filterFeatures(Object value)
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
      
      ClassModelSet answer = new ClassModelSet();
      
      for (ClassModel obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getFeatures()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the ClassModel object passed as parameter to the Features attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Features attributes.
    */
   public ClassModelSet withFeatures(Feature value)
   {
      for (ClassModel obj : this)
      {
         obj.withFeatures(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the ClassModel object passed as parameter from the Features attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public ClassModelSet withoutFeatures(Feature value)
   {
      for (ClassModel obj : this)
      {
         obj.withoutFeatures(value);
      }
      
      return this;
   }

}
