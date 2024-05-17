package com.employee.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AppUtils {

	public static enum Activity {
		Add,
		Update,
		Delete,
		Fetch
	}
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	public static String writeValueAsString(Object object) throws Exception {
		try {
			return OBJECT_MAPPER.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		throw new Exception("Unable to convert object to json!!!");
	}
	
	public static Object convertJsonToObj(String json, Class<Object> valueType) throws Exception {
		try {
			return OBJECT_MAPPER.readValue(json, valueType);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		throw new Exception("Unable to convert json to object!!!");
	}
}
