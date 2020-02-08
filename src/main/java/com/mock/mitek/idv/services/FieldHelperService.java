package com.mock.mitek.idv.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.management.RuntimeErrorException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mock.mitek.idv.constants.AppConstants;

@Service
public class FieldHelperService {

	public void addField(JsonNode apiDetail, String appName) throws JsonProcessingException {
		boolean code = validateStatusCode(apiDetail.get("statusCode").asText());
		if (!code) {
			throw new RuntimeErrorException(null, "Invalid HttpStatus code");
		}
		Map<String, String> apiDetails = new HashMap<>();

		apiDetails = new ObjectMapper().convertValue(apiDetail, new TypeReference<Map<String, String>>() {
		});
		Set<Map<String, String>> list = null;
		if (AppConstants.getAppApiDetails().get(appName) == null) {
			list = new HashSet<>();
		} else {
			list = AppConstants.getAppApiDetails().get(appName);
		}
		list.add(apiDetails);
		AppConstants.getAppApiDetails().put(appName, list);
	}

	private boolean validateStatusCode(String asText) {
		for(HttpStatus s : Arrays.asList(HttpStatus.values())) {
			if(String.valueOf(s.value()).equalsIgnoreCase(asText)) {
				return true;
			}
		}
		return false;
	}

	public void deleteField(JsonNode apiDetail, String appName) {
		Set<Map<String, String>> details = AppConstants.getAppApiDetails().get(appName);
		details.remove(new ObjectMapper().convertValue(apiDetail, new TypeReference<Map<String, String>>() {
		}));
		AppConstants.getAppApiDetails().put(appName, details);
		System.out.println("s" + AppConstants.getAppApiDetails());
	}

}
