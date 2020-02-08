package com.mock.mitek.idv.services;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class DemoService {

	@Autowired
	MockService mockService;

	public ResponseEntity<JsonNode> mockIt(HttpServletRequest request) throws IOException {

		return mockService.getAutoVerifyResponse(null, request.getAttribute("appName").toString(),
				request.getAttribute("abcd").toString(), request.getMethod());
	}

}
