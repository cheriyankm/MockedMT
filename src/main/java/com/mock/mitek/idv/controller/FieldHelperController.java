package com.mock.mitek.idv.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@PostMapping(value = "/add", consumes = "application/json")
	public void addField(@RequestBody JsonNode apiDetail) throws JsonProcessingException {
		fieldHelperService.addField(apiDetail);
	}
	
	@PostMapping(value = "/remove", consumes = "application/json")
	public void deleteField(@RequestBody JsonNode apiDetail) throws JsonProcessingException {
		fieldHelperService.deleteField(apiDetail);
	}
}
