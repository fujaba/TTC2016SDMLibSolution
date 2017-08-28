package de.unikassel.ttc2016.model.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = new IdMap().withSession(session);
      jsonIdMap.with(new AttributeCreator());
      jsonIdMap.with(new AttributePOCreator());
      jsonIdMap.with(new ClassCreator());
      jsonIdMap.with(new ClassPOCreator());
      jsonIdMap.with(new ClassModelCreator());
      jsonIdMap.with(new ClassModelPOCreator());
      jsonIdMap.with(new FeatureCreator());
      jsonIdMap.with(new FeaturePOCreator());
      jsonIdMap.with(new MethodCreator());
      jsonIdMap.with(new MethodPOCreator());
      jsonIdMap.with(new NamedElementCreator());
      jsonIdMap.with(new NamedElementPOCreator());
      return jsonIdMap;
   }
}
