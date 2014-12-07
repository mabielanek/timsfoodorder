package com.timsmeet.services.mapper;

public interface Mapper<S, T> {
	void map(S source, T target);
	void inverseMap(T source, S target);
}
