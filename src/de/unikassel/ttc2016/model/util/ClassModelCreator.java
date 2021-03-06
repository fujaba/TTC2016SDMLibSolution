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

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.unikassel.ttc2016.model.ClassModel;
import de.uniks.networkparser.IdMap;
import de.unikassel.ttc2016.model.NamedElement;
import de.unikassel.ttc2016.model.Class;
import de.unikassel.ttc2016.model.Feature;

public class ClassModelCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      NamedElement.PROPERTY_NAME,
      ClassModel.PROPERTY_CLASSES,
      ClassModel.PROPERTY_FEATURES,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new ClassModel();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (NamedElement.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((NamedElement) target).getName();
      }

      if (ClassModel.PROPERTY_CLASSES.equalsIgnoreCase(attribute))
      {
         return ((ClassModel) target).getClasses();
      }

      if (ClassModel.PROPERTY_FEATURES.equalsIgnoreCase(attribute))
      {
         return ((ClassModel) target).getFeatures();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (NamedElement.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((NamedElement) target).setName((String) value);
         return true;
      }

      if(SendableEntityCreator.REMOVE_YOU.equals(type)) {
           ((ClassModel)target).removeYou();
           return true;
      }
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (ClassModel.PROPERTY_CLASSES.equalsIgnoreCase(attrName))
      {
         ((ClassModel) target).withClasses((Class) value);
         return true;
      }
      
      if ((ClassModel.PROPERTY_CLASSES + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ClassModel) target).withoutClasses((Class) value);
         return true;
      }

      if (ClassModel.PROPERTY_FEATURES.equalsIgnoreCase(attrName))
      {
         ((ClassModel) target).withFeatures((Feature) value);
         return true;
      }
      
      if ((ClassModel.PROPERTY_FEATURES + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ClassModel) target).withoutFeatures((Feature) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return de.unikassel.ttc2016.model.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((ClassModel) entity).removeYou();
   }
}
