package com.timsmeet.services.mapper;

import org.hibernate.collection.spi.PersistentCollection;

public class HibernateMapperHelper {

	public static boolean isCollectionInitialized(Object collection) {
		if(collection != null) {
			if(collection instanceof PersistentCollection) {
				if(!((PersistentCollection)collection).wasInitialized()) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
