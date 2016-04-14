package de.unikassel.ttc2016.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import de.unikassel.ttc2016.model.Class;

public class ClassPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ClassPO(new Class[]{});
      } else {
          return new ClassPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return de.unikassel.ttc2016.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
