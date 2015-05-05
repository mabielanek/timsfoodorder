package com.timsmeet.services.find;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.google.common.base.Predicate;
import com.google.common.base.Verify;
import com.google.common.collect.Collections2;
import com.timsmeet.services.find.entity.IdAccessor;

@Service
public class FindEntityWithIdAccessor<T> {

	private IdAccessor<T, Long> idAccessor;
	
	public IdAccessor<T, Long> getIdAccessor() {
		return idAccessor;
	}

	public void setIdAccessor(IdAccessor<T, Long> idAccessor) {
		this.idAccessor = idAccessor;
	}

	public T findById(Collection<T> collection, final Long entityId) {
		Verify.verifyNotNull(entityId, "entityId can't be null when findById");
		Verify.verify(idAccessor != null, "IdAccessor can't be null when findById, for id=%s", entityId);
		
		Collection<T> matchingEntities = Collections2.filter(collection, new Predicate<T>() {

			@Override
			public boolean apply(T input) {
				return idAccessor.getIdValue(input).equals(entityId);
			}
		});
		
		if (matchingEntities.size() == 0) {
			throw new IllegalArgumentException("Existing entity with key: " + entityId + " not found.");
		} else if (matchingEntities.size() > 1) {
			throw new IllegalStateException("More than one entity with key: " + entityId + " found in collection.");
		}
		return matchingEntities.iterator().next();
	}
	
}
