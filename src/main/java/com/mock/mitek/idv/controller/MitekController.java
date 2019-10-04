package com.mock.mitek.idv.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.mock.mitek.idv.services.MitekService;

@RestController
public class MitekController {

	@Autowired
	MitekService mitekService;

	@RequestMapping(value = "/api/verify/v2/dossier", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> autoVerify(@RequestBody JsonNode node) throws IOException {
		String a = "/api/verify/v2/dossier";
		return mitekService.getAutoVerifyResponse(node, a, RequestMethod.POST);
	}

	@RequestMapping(value = "/oauth2/token", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> getToken1() throws IOException {
		String a = "/oauth2/token";
		return mitekService.getAutoVerifyResponse(null, a, RequestMethod.POST);
	}

	@RequestMapping(value = "/connect/token", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> getToken2() throws IOException {
		String a = "/connect/token";
		return mitekService.getAutoVerifyResponse(null, a, RequestMethod.POST);
	}

	@RequestMapping(value = "/identity/verify/v3/id-document/manual", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> manualVerify(@RequestBody JsonNode node) throws IOException {
		String a = "/identity/verify/v3/id-document/manual";
		return mitekService.getAutoVerifyResponse(node, a, RequestMethod.POST);
	}

	@RequestMapping(value = "/identity/verify/v3/id-document/manual/{retrievalId}", method = RequestMethod.GET)
	public ResponseEntity<JsonNode> retrival(@PathVariable("retrievalId") String retrievalId) throws IOException {
		String a = "/identity/verify/v3/id-document/manual/{retrievalId}";
		return mitekService.getAutoVerifyResponse(null, a, RequestMethod.GET);
	}

	@RequestMapping(value = "/identity/v3/poll", method = RequestMethod.GET)
	public ResponseEntity<JsonNode> polling() throws IOException {
		String a = "/identity/v3/poll";
		return mitekService.getAutoVerifyResponse(null, a, RequestMethod.GET);
	}

	@RequestMapping(value = "/identity/facecomparison/v3/auto", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> autoFaceCompare(@RequestBody JsonNode node) throws IOException {
		String a = "/identity/facecomparison/v3/auto";
		return mitekService.getAutoVerifyResponse(node, a, RequestMethod.POST);
	}

}
