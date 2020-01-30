package com.mock.mitek.idv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.mock.mitek.idv.services.FieldHelperService;

@RestController
@RequestMapping("/cherry")
public class FieldHelperController {

	@Autowired
	FieldHelperService fieldHelperService;
	
	@PostMapping(value = "/add/{appName}", consumes = "application/json")
	public void addField(@RequestBody JsonNode apiDetail, @PathVariable String appName) throws JsonProcessingException {
		fieldHelperService.addField(apiDetail, appName);
	}
	
	@PostMapping(value = "/remove/{appName}", consumes = "application/json")
	public void deleteField(@RequestBody JsonNode apiDetail, @PathVariable String appName) throws JsonProcessingException {
		fieldHelperService.deleteField(apiDetail, appName);
	}
}
