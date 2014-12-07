package com.timsmeet.services.mapper;

import java.util.Set;

public class DtoEntityMapperImpl {

	Set<String> includedFields; // null or empty - map all fields
	Set<String> excludedFields; // field to exclude from mapping - always
	Set<String> inverseExcludedFields; //fields to exclude only in inverse mapping
	Set<String> forwardExcludeFields; //fields to exclude only in forward mapping
	
	//Set<String, MappingStrategy> mapFieldsWithStrategy; // fields to mapp using specified strategy (not null, collection, embeded object etc) 
}



