package com.mock.mitek.idv.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.mock.mitek.idv.services.DemoService;

@RestController
public class DemoController {

	@Autowired
	DemoService demoService;

	@GetMapping(value = "/mockitcontroller")
	public ResponseEntity<JsonNode> getMockIt(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return demoService.mockIt(request);
	}

	@PostMapping(value = "/mockitcontroller")
	public ResponseEntity<JsonNode> postMockIt(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return demoService.mockIt(request);
	}

	@DeleteMapping(value = "/mockitcontroller")
	public ResponseEntity<JsonNode> deleteMockIt(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return demoService.mockIt(request);
	}

	@PutMapping(value = "/mockitcontroller")
	public ResponseEntity<JsonNode> putMockIt(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return demoService.mockIt(request);
	}

	@PatchMapping(value = "/mockitcontroller")
	public ResponseEntity<JsonNode> patchMockIt(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return demoService.mockIt(request);
	}
}
