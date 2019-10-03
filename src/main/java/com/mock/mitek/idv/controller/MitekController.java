package com.mock.mitek.idv.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.mock.mitek.idv.services.MitekService;

@RestController
@RequestMapping("/mitek")
public class MitekController {

	@Autowired
	MitekService mitekService;

	@RequestMapping(value = "/getFiles", method = RequestMethod.GET)
	public List<String> getFiles() throws IOException {
		return mitekService.getAllFiles();
	}

	@RequestMapping(value = "/setFiles/{fileName}", method = RequestMethod.GET)
	public void setFiles(@PathVariable("fileName") String fileName) throws IOException {
		mitekService.setFileName(fileName);
	}

	@RequestMapping(value = "/getFile", method = RequestMethod.GET)
	public String getFile() throws IOException {
		return mitekService.getFileName();
	}

	@RequestMapping(value = "/auto", method = RequestMethod.POST)
	public JsonNode auto(@RequestBody JsonNode request) throws IOException {
		return mitekService.getAutoResult(request);
	}
}
