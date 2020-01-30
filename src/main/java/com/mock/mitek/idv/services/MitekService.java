package com.mock.mitek.idv.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mock.mitek.idv.constants.AppConstants;

@Service
public class MitekService {

	private static final String CUSTOMER_REF_ID = "customerReferenceId";
	@Autowired
	ObjectMapper objectMapper;

	public ResponseEntity<JsonNode> getAutoVerifyResponse(JsonNode node, String api, RequestMethod method, String a)
			throws IOException {
		ResponseEntity<JsonNode> result = null;
		Map<String, String> o = AppConstants.getFileManager().stream().filter(m -> m.get("api").equalsIgnoreCase(api))
				.findFirst().get();
		ObjectNode jsonObj = null;
		if (200 == Double.parseDouble(o.get("statusCode"))) {
			String obj = o.get("response");
			String validC = null;
			jsonObj = (ObjectNode) objectMapper.readTree(obj);
			/*
			 * if (get.equals(RequestMethod.POST) && !api.equalsIgnoreCase("/connect/token")
			 * && !api.equalsIgnoreCase("/oauth2/token")) { validC =
			 * node.get(CUSTOMER_REF_ID).asText(); AppConstants.setRefId(validC); } if
			 * (api.equalsIgnoreCase("/api/verify/v2/dossier")) { ObjectNode dmdata =
			 * (ObjectNode) jsonObj.get("dossierMetadata"); dmdata.put(CUSTOMER_REF_ID,
			 * validC); } if
			 * (api.equalsIgnoreCase("/identity/verify/v3/id-document/manual")) {
			 * jsonObj.put(CUSTOMER_REF_ID, validC);
			 * AppConstants.setCheckId(jsonObj.get("requestId").asText()); }
			 */
			/*
			 * if(api.equalsIgnoreCase("/identity/v3/poll")) { List<Object> listOfRequest =
			 * objectMapper.readTree(jsonObj.get("requests"), List.class); }
			 */
			result = new ResponseEntity<JsonNode>(jsonObj, HttpStatus.OK);
		} else {
			result = new ResponseEntity<JsonNode>(objectMapper.readTree(o.get("response")),
					HttpStatus.valueOf((int) Double.parseDouble(o.get("statusCode"))));
		}
		return result;
	}

	public ResponseEntity<JsonNode> getAutoVerifyResponse(JsonNode node, String api, String method) throws IOException {
		ResponseEntity<JsonNode> result = null;
		try {
			Map<String, String> o = AppConstants.getFileManager().stream()
					.filter(m -> m.get("api").equalsIgnoreCase(api) && m.get("requestMethod").equalsIgnoreCase(method))
					.findFirst().get();

			return new ResponseEntity<JsonNode>(objectMapper.readTree(o.get("response")),
					HttpStatus.valueOf((int) Double.parseDouble(o.get("statusCode"))));
		} catch (NoSuchElementException e) {
			String message = "{\"errorMessage\":\"There is no ".concat(api).concat(" API available\"}");
			Map<String, String> o = doRegexCheck(api, method);
			JsonNode body = null;
			if(o == null) {
				body = objectMapper.readTree(message);
				return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
			}else {
				body = objectMapper.readTree(o.get("response"));
				return new ResponseEntity<>(body, HttpStatus.valueOf((int) Double.parseDouble(o.get("statusCode"))));
			}
		} catch (NullPointerException e) {
			String message = "{\"errorMessage\":\"No APIs available\"}";
			JsonNode body = objectMapper.readTree(message);
			return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		}
	}

	private Map<String, String> doRegexCheck(String api, String method) {
		Map<String, String> result = null;
		for(Map<String, String> a : AppConstants.getFileManager()) {
			if(testRegex(a.get("api"), api)) {
				result = a;
				break;
			}
		}
		return result;
	}
	
	private boolean testRegex(String storedApi, String api) {
		
		String finalRegex="^";
		boolean flag = false;
		if(storedApi.contains("{") && storedApi.contains("}")) {
			char[] charArray = storedApi.toCharArray();
			for(Character k : charArray) {
				if(k.equals('{')) {
					flag = true;
					finalRegex = finalRegex.concat("[A-Za-z0-9_@.%#&+-]*");
				}else if(k.equals('}')){
					flag = false;
				}else {
					if(!flag) {
						finalRegex = finalRegex.concat(k.toString());
					}
				}
			}
			finalRegex = finalRegex.concat("$");
			flag=false;
		}else {
			flag=true;
		}
		if(!flag) {
			Pattern p = Pattern.compile(finalRegex);//. represents single character  
			Matcher m = p.matcher(api);  
			return m.matches();
		}else {
			return false;
		}
	}

	
}
