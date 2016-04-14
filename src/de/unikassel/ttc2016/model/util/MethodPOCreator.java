package de.unikassel.ttc2016.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import de.unikassel.ttc2016.model.Method;

public class MethodPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new MethodPO(new Method[]{});
      } else {
          return new MethodPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return de.unikassel.ttc2016.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
