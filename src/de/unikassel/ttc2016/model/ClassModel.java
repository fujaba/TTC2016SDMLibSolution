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

import de.unikassel.ttc2016.model.NamedElement;
import de.unikassel.ttc2016.model.util.ClassSet;
import de.unikassel.ttc2016.model.Class;
import de.unikassel.ttc2016.model.Attribute;
import de.unikassel.ttc2016.model.Method;
import de.unikassel.ttc2016.model.util.FeatureSet;
import de.unikassel.ttc2016.model.Feature;
   /**
    * 
    * @see <a href='../../../../../src/de/unikassel/ttc2016/classmodel/GenClassesFromEcore.java'>GenClassesFromEcore.java</a>
 */
   public  class ClassModel extends NamedElement
{

   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
   
      super.removeYou();

      withoutClasses(this.getClasses().toArray(new Class[this.getClasses().size()]));
      withoutFeatures(this.getFeatures().toArray(new Feature[this.getFeatures().size()]));
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
    * ClassModel ----------------------------------- Class
    *              classmodel                   classes
    * </pre>
    */
   
   public static final String PROPERTY_CLASSES = "classes";

   private ClassSet classes = null;
   
   public ClassSet getClasses()
   {
      if (this.classes == null)
      {
         return ClassSet.EMPTY_SET;
      }
   
      return this.classes;
   }

   public ClassModel withClasses(Class... value)
   {
      if(value==null){
         return this;
      }
      for (Class item : value)
      {
         if (item != null)
         {
            if (this.classes == null)
            {
               this.classes = new ClassSet();
            }
            
            boolean changed = this.classes.add (item);

            if (changed)
            {
               item.withClassmodel(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_CLASSES, null, item);
            }
         }
      }
      return this;
   } 

   public ClassModel withoutClasses(Class... value)
   {
      for (Class item : value)
      {
         if ((this.classes != null) && (item != null))
         {
            if (this.classes.remove(item))
            {
               item.setClassmodel(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_CLASSES, item, null);
            }
         }
      }
      return this;
   }

   public Class createClasses()
   {
      Class value = new Class();
      withClasses(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * ClassModel ----------------------------------- Feature
    *              classmodel                   features
    * </pre>
    */
   
   public static final String PROPERTY_FEATURES = "features";

   private FeatureSet features = null;
   
   public FeatureSet getFeatures()
   {
      if (this.features == null)
      {
         return FeatureSet.EMPTY_SET;
      }
   
      return this.features;
   }

   public ClassModel withFeatures(Feature... value)
   {
      if(value==null){
         return this;
      }
      for (Feature item : value)
      {
         if (item != null)
         {
            if (this.features == null)
            {
               this.features = new FeatureSet();
            }
            
            boolean changed = this.features.add (item);

            if (changed)
            {
               item.withClassmodel(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_FEATURES, null, item);
            }
         }
      }
      return this;
   } 

   public ClassModel withoutFeatures(Feature... value)
   {
      for (Feature item : value)
      {
         if ((this.features != null) && (item != null))
         {
            if (this.features.remove(item))
            {
               item.setClassmodel(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_FEATURES, item, null);
            }
         }
      }
      return this;
   }

   public Feature createFeatures()
   {
      Feature value = new Feature();
      withFeatures(value);
      return value;
   } 

   public Attribute createFeaturesAttribute()
   {
      Attribute value = new Attribute();
      withFeatures(value);
      return value;
   } 

   public Method createFeaturesMethod()
   {
      Method value = new Method();
      withFeatures(value);
      return value;
   } 
}
