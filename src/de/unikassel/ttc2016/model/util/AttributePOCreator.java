package de.unikassel.ttc2016.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import de.unikassel.ttc2016.model.Attribute;

public class AttributePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new AttributePO(new Attribute[]{});
      } else {
          return new AttributePO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return de.unikassel.ttc2016.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
