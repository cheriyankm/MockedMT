package com.mock.mitek.idv.controller;

import java.util.Arrays;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public String getMockIt(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("inside controller***********");
		
		return demoService.mockIt(request);
	}

	@PostMapping(value = "/mockitcontroller")
	public String postMockIt(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("inside controller***********");
		
		return demoService.mockIt(request);
	}
}
