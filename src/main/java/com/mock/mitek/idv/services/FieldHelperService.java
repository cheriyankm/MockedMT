package com.mock.mitek.idv.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mock.mitek.idv.constants.AppConstants;

@Service
public class FieldHelperService {

	public void addField(JsonNode apiDetail) throws JsonProcessingException {
		System.out.println("inside field service! : "+new ObjectMapper().writeValueAsString(apiDetail));
		Map<String, String> apiDetails = new HashMap<>();
		
		apiDetails = new ObjectMapper().convertValue(apiDetail, new TypeReference<Map<String, String>>(){});
		Set<Map<String, String>> list = AppConstants.getFileManager()==null?new HashSet<>():AppConstants.getFileManager();
		list.add(apiDetails);
		AppConstants.setFileManager(list);
	}

	public void deleteField(JsonNode apiDetail) {
		Set<Map<String, String>> details = AppConstants.getFileManager();
		details.remove(new ObjectMapper().convertValue(apiDetail, new TypeReference<Map<String, String>>(){}));
		AppConstants.setFileManager(details);
	}

}
