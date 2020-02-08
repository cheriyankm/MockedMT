package com.mock.mitek.idv.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mock.mitek.idv.constants.AppConstants;

@Service
public class AppService {
	
	@Autowired
	ObjectMapper objectMapper;

	public JsonNode insert(String appName) throws IOException {
		if(AppConstants.getAppApiDetails()!=null && AppConstants.getAppApiDetails().containsKey(appName)) {
			return objectMapper.readTree("{\"key\":\"error\",\"value\":\"App already exists\"}");
		}
		if(AppConstants.getAppApiDetails() == null) {
			Map<String, Set<Map<String, String>>> a = new HashMap<>();
			AppConstants.setAppApiDetails(a);
		}
		AppConstants.getAppApiDetails().put(appName, null);
		return objectMapper.readTree("{\"key\":\"success\",\"value\":\""+appName+"\"}");
	}

	public Set<String> getApps() {
		return AppConstants.getAppApiDetails()!=null?AppConstants.getAppApiDetails().keySet():new HashSet<>();
	}

}
