package com.oauth.security.utils;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ObjectCloner {

	private static final ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
	}

	@SuppressWarnings("unchecked")
	public <T> T clone(Class<T> object) {

		try {
			byte[] bytes = objectMapper.writeValueAsBytes(object);
			return (T) objectMapper.readValue(bytes, object.getClass());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Cloning issue : " + e.getMessage());
		}
	}

}
