package com.mock.mitek.idv.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.mock.mitek.idv.services.AppService;

@RestController
@RequestMapping(value="/app")
public class AppController {

	@Autowired
	AppService appService;

	@GetMapping(value = "/insert/{appName}")
	public JsonNode insert(@PathVariable String appName) throws IOException {
		return appService.insert(appName);
	}
	
	@GetMapping(value = "/getallapp")
	public Set<String> getApps() throws IOException {
		return appService.getApps();
	}
}
