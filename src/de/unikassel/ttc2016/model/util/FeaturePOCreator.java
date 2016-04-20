package de.unikassel.ttc2016.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import de.unikassel.ttc2016.model.Feature;

public class FeaturePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new FeaturePO(new Feature[]{});
      } else {
          return new FeaturePO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return de.unikassel.ttc2016.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
