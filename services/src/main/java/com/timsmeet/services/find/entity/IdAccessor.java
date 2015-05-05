package com.timsmeet.services.find.entity;

public interface IdAccessor<T, ID> {
	ID getIdValue(T entity);
}