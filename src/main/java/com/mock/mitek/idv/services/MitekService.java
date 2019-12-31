package com.mock.mitek.idv.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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

	public ResponseEntity<JsonNode> getAutoVerifyResponse(JsonNode node, String api, RequestMethod get) throws IOException {
		ResponseEntity<JsonNode> result = null;
		Map<String, String> o = AppConstants.getFileManager().stream().filter(m -> m.get("api").equalsIgnoreCase(api))
				.findFirst().get();
		ObjectNode jsonObj = null;
		if (200 == Double.parseDouble(o.get("statusCode"))) {
			String obj = o.get("response");
			String validC = null;
			jsonObj = (ObjectNode) objectMapper.readTree(obj);
			/*if (get.equals(RequestMethod.POST) && !api.equalsIgnoreCase("/connect/token") && !api.equalsIgnoreCase("/oauth2/token")) {
				validC = node.get(CUSTOMER_REF_ID).asText();
				AppConstants.setRefId(validC);
			}
			if (api.equalsIgnoreCase("/api/verify/v2/dossier")) {
				ObjectNode dmdata = (ObjectNode) jsonObj.get("dossierMetadata");
				dmdata.put(CUSTOMER_REF_ID, validC);
			}
			if (api.equalsIgnoreCase("/identity/verify/v3/id-document/manual")) {
				jsonObj.put(CUSTOMER_REF_ID, validC);
				AppConstants.setCheckId(jsonObj.get("requestId").asText());
			}*/
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

}
