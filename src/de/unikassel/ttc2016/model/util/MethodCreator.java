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

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.IdMap;
import de.unikassel.ttc2016.model.Method;
import de.unikassel.ttc2016.model.NamedElement;
import de.unikassel.ttc2016.model.ClassModel;
import de.unikassel.ttc2016.model.Feature;
import de.unikassel.ttc2016.model.Class;
import de.unikassel.ttc2016.model.Attribute;

public class MethodCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      NamedElement.PROPERTY_NAME,
      Feature.PROPERTY_CLASSMODEL,
      Feature.PROPERTY_ISENCAPSULATEDBY,
      Method.PROPERTY_DATADEPENDENCY,
      Method.PROPERTY_METHOD,
      Method.PROPERTY_FUNCTIONALDEPENDENCY,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Method();
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

      if (Method.PROPERTY_CLASSMODEL.equalsIgnoreCase(attribute))
      {
         return ((Method) target).getClassmodel();
      }

      if (Method.PROPERTY_ISENCAPSULATEDBY.equalsIgnoreCase(attribute))
      {
         return ((Method) target).getIsEncapsulatedBy();
      }

      if (Method.PROPERTY_DATADEPENDENCY.equalsIgnoreCase(attribute))
      {
         return ((Method) target).getDataDependency();
      }

      if (Method.PROPERTY_METHOD.equalsIgnoreCase(attribute))
      {
         return ((Method) target).getMethod();
      }

      if (Method.PROPERTY_FUNCTIONALDEPENDENCY.equalsIgnoreCase(attribute))
      {
         return ((Method) target).getFunctionalDependency();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (NamedElement.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((NamedElement) target).withName((String) value);
         return true;
      }

      if (IdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Method.PROPERTY_CLASSMODEL.equalsIgnoreCase(attrName))
      {
         ((Method) target).setClassmodel((ClassModel) value);
         return true;
      }

      if (Method.PROPERTY_ISENCAPSULATEDBY.equalsIgnoreCase(attrName))
      {
         ((Method) target).setIsEncapsulatedBy((Class) value);
         return true;
      }

      if (Method.PROPERTY_DATADEPENDENCY.equalsIgnoreCase(attrName))
      {
         ((Method) target).withDataDependency((Attribute) value);
         return true;
      }
      
      if ((Method.PROPERTY_DATADEPENDENCY + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Method) target).withoutDataDependency((Attribute) value);
         return true;
      }

      if (Method.PROPERTY_METHOD.equalsIgnoreCase(attrName))
      {
         ((Method) target).setMethod((Method) value);
         return true;
      }

      if (Method.PROPERTY_FUNCTIONALDEPENDENCY.equalsIgnoreCase(attrName))
      {
         ((Method) target).withFunctionalDependency((Method) value);
         return true;
      }
      
      if ((Method.PROPERTY_FUNCTIONALDEPENDENCY + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Method) target).withoutFunctionalDependency((Method) value);
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
      ((Method) entity).removeYou();
   }
}
