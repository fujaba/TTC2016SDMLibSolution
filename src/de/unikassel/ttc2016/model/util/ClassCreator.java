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
import de.unikassel.ttc2016.model.Class;
import de.uniks.networkparser.IdMap;
import de.unikassel.ttc2016.model.NamedElement;
import de.unikassel.ttc2016.model.ClassModel;
import de.unikassel.ttc2016.model.Feature;

public class ClassCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      NamedElement.PROPERTY_NAME,
      Class.PROPERTY_CLASSMODEL,
      Class.PROPERTY_ENCAPSULATES,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Class();
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

      if (Class.PROPERTY_CLASSMODEL.equalsIgnoreCase(attribute))
      {
         return ((Class) target).getClassmodel();
      }

      if (Class.PROPERTY_ENCAPSULATES.equalsIgnoreCase(attribute))
      {
         return ((Class) target).getEncapsulates();
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
           ((Class)target).removeYou();
           return true;
      }
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Class.PROPERTY_CLASSMODEL.equalsIgnoreCase(attrName))
      {
         ((Class) target).setClassmodel((ClassModel) value);
         return true;
      }

      if (Class.PROPERTY_ENCAPSULATES.equalsIgnoreCase(attrName))
      {
         ((Class) target).withEncapsulates((Feature) value);
         return true;
      }
      
      if ((Class.PROPERTY_ENCAPSULATES + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Class) target).withoutEncapsulates((Feature) value);
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
      ((Class) entity).removeYou();
   }
}
