package com.timsmeet.rest;

import java.io.IOException;
import java.nio.charset.Charset;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class RestTestUtil {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	public static byte[] convertObjectToJsonBytes(Object object)
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}

	public static <T> T convertJsonBytesToObject(byte[] bytes,
			Class<T> valueType) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(bytes, valueType);
	}

	public static <T> T[] convertJsonBytesToArray(byte[] bytes, Class<T> valueType) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(bytes, TypeFactory.defaultInstance().constructArrayType(valueType));
	}

}
