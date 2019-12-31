package com.mock.mitek.idv.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class DemoService {

	@Autowired
	MitekService mitekService;
	
	public void insert() {
		System.out.println("insert");
	}

	public void select() {
		System.out.println("select");
	}

	public void delete() {
		System.out.println("delete");
	}

	public ResponseEntity<JsonNode> mockIt(HttpServletRequest request) throws IOException {
		
		return mitekService.getAutoVerifyResponse(null, request.getAttribute("abcd").toString(), RequestMethod.valueOf(request.getMethod()));
	}

	
}
