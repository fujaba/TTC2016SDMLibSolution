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
import org.sdmlib.models.modelsets.ObjectSet;
import de.unikassel.ttc2016.model.util.ClassModelSet;
import de.unikassel.ttc2016.model.ClassModel;
import de.unikassel.ttc2016.model.util.ClassSet;
import de.unikassel.ttc2016.model.Class;
import java.util.Collections;
import de.unikassel.ttc2016.model.util.MethodSet;

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

   /**
    * Loop through the current set of Method objects and collect a set of the ClassModel objects reached via classmodel. 
    * 
    * @return Set of ClassModel objects reachable via classmodel
    */
   public ClassModelSet getClassmodel()
   {
      ClassModelSet result = new ClassModelSet();
      
      for (Method obj : this)
      {
         result.with(obj.getClassmodel());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Method objects and collect all contained objects with reference classmodel pointing to the object passed as parameter. 
    * 
    * @param value The object required as classmodel neighbor of the collected results. 
    * 
    * @return Set of ClassModel objects referring to value via classmodel
    */
   public MethodSet filterClassmodel(Object value)
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
      
      MethodSet answer = new MethodSet();
      
      for (Method obj : this)
      {
         if (neighbors.contains(obj.getClassmodel()) || (neighbors.isEmpty() && obj.getClassmodel() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Method object passed as parameter to the Classmodel attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Classmodel attributes.
    */
   public MethodSet withClassmodel(ClassModel value)
   {
      for (Method obj : this)
      {
         obj.withClassmodel(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Method objects and collect a set of the Class objects reached via isEncapsulatedBy. 
    * 
    * @return Set of Class objects reachable via isEncapsulatedBy
    */
   public ClassSet getIsEncapsulatedBy()
   {
      ClassSet result = new ClassSet();
      
      for (Method obj : this)
      {
         result.with(obj.getIsEncapsulatedBy());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Method objects and collect all contained objects with reference isEncapsulatedBy pointing to the object passed as parameter. 
    * 
    * @param value The object required as isEncapsulatedBy neighbor of the collected results. 
    * 
    * @return Set of Class objects referring to value via isEncapsulatedBy
    */
   public MethodSet filterIsEncapsulatedBy(Object value)
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
      
      MethodSet answer = new MethodSet();
      
      for (Method obj : this)
      {
         if (neighbors.contains(obj.getIsEncapsulatedBy()) || (neighbors.isEmpty() && obj.getIsEncapsulatedBy() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Method object passed as parameter to the IsEncapsulatedBy attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their IsEncapsulatedBy attributes.
    */
   public MethodSet withIsEncapsulatedBy(Class value)
   {
      for (Method obj : this)
      {
         obj.withIsEncapsulatedBy(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Method objects and collect a set of the Method objects reached via usedByMethods. 
    * 
    * @return Set of Method objects reachable via usedByMethods
    */
   public MethodSet getUsedByMethods()
   {
      MethodSet result = new MethodSet();
      
      for (Method obj : this)
      {
         result.with(obj.getUsedByMethods());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Method objects and collect all contained objects with reference usedByMethods pointing to the object passed as parameter. 
    * 
    * @param value The object required as usedByMethods neighbor of the collected results. 
    * 
    * @return Set of Method objects referring to value via usedByMethods
    */
   public MethodSet filterUsedByMethods(Object value)
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
      
      MethodSet answer = new MethodSet();
      
      for (Method obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getUsedByMethods()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow usedByMethods reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of Method objects reachable via usedByMethods transitively (including the start set)
    */
   public MethodSet getUsedByMethodsTransitive()
   {
      MethodSet todo = new MethodSet().with(this);
      
      MethodSet result = new MethodSet();
      
      while ( ! todo.isEmpty())
      {
         Method current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getUsedByMethods()).minus(result);
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the Method object passed as parameter to the UsedByMethods attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their UsedByMethods attributes.
    */
   public MethodSet withUsedByMethods(Method value)
   {
      for (Method obj : this)
      {
         obj.withUsedByMethods(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Method object passed as parameter from the UsedByMethods attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public MethodSet withoutUsedByMethods(Method value)
   {
      for (Method obj : this)
      {
         obj.withoutUsedByMethods(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Method objects and collect a set of the Method objects reached via functionalDependency. 
    * 
    * @return Set of Method objects reachable via functionalDependency
    */
   public MethodSet getFunctionalDependency()
   {
      MethodSet result = new MethodSet();
      
      for (Method obj : this)
      {
         result.with(obj.getFunctionalDependency());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Method objects and collect all contained objects with reference functionalDependency pointing to the object passed as parameter. 
    * 
    * @param value The object required as functionalDependency neighbor of the collected results. 
    * 
    * @return Set of Method objects referring to value via functionalDependency
    */
   public MethodSet filterFunctionalDependency(Object value)
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
      
      MethodSet answer = new MethodSet();
      
      for (Method obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getFunctionalDependency()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow functionalDependency reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of Method objects reachable via functionalDependency transitively (including the start set)
    */
   public MethodSet getFunctionalDependencyTransitive()
   {
      MethodSet todo = new MethodSet().with(this);
      
      MethodSet result = new MethodSet();
      
      while ( ! todo.isEmpty())
      {
         Method current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getFunctionalDependency()).minus(result);
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the Method object passed as parameter to the FunctionalDependency attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their FunctionalDependency attributes.
    */
   public MethodSet withFunctionalDependency(Method value)
   {
      for (Method obj : this)
      {
         obj.withFunctionalDependency(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Method object passed as parameter from the FunctionalDependency attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public MethodSet withoutFunctionalDependency(Method value)
   {
      for (Method obj : this)
      {
         obj.withoutFunctionalDependency(value);
      }
      
      return this;
   }

}
