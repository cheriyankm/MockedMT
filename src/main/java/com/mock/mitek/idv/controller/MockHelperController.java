package com.mock.mitek.idv.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.mock.mitek.idv.services.FileReadService;

@RestController
@RequestMapping("/mitek")
public class MockHelperController {

	@Autowired
	FileReadService fileReadService;

	@RequestMapping(value = "/getFiles", method = RequestMethod.GET)
	public List<String> getFiles() throws IOException {
		return fileReadService.getAllFiles();
	}

	@RequestMapping(value = "/setFile/{fileName}/{appName}", method = RequestMethod.GET)
	public void setFiles(@PathVariable("fileName") String fileName, @PathVariable String appName) throws IOException {
		fileReadService.setFileName(fileName, appName);
	}

	@RequestMapping(value = "/getFile", method = RequestMethod.GET)
	public List<String> getFile() throws IOException {
		return Collections.singletonList(fileReadService.getFileName());
	}

	@RequestMapping(value = "/response/mocked", method = RequestMethod.GET)
	public JsonNode getMockedResponse() throws IOException {
		return fileReadService.getAutoResult();
	}

	@RequestMapping(value = "/response/mocked/cache/{appName}", method = RequestMethod.GET)
	public JsonNode getMockedResponseFromCache(@PathVariable String appName) throws IOException {
		return fileReadService.getAutoResultFromCache(appName);
	}
}
