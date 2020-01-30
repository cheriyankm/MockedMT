package com.mock.mitek.idv.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.mock.mitek.idv.services.DemoService;

@RestController
public class DemoController {

	@Autowired
	DemoService demoService;

	@GetMapping(value = "/insert")
	public void insert() {
		demoService.insert();
	}

	@GetMapping(value = "/select")
	public void select() {
		demoService.select();
	}

	@GetMapping(value = "/delete")
	public void delete() {
		demoService.delete();
	}

	@GetMapping(value = "/mockitcontroller")
	public ResponseEntity<JsonNode> getMockIt(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return demoService.mockIt(request);
	}

	@PostMapping(value = "/mockitcontroller")
	public ResponseEntity<JsonNode> postMockIt(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return demoService.mockIt(request);
	}
}
