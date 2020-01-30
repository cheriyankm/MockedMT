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
import com.mock.mitek.idv.services.MitekHelperService;

@RestController
@RequestMapping("/mitek")
public class MitekHelperController {

	@Autowired
	MitekHelperService mitekHelperService;

	@RequestMapping(value = "/getFiles", method = RequestMethod.GET)
	public List<String> getFiles() throws IOException {
		return mitekHelperService.getAllFiles();
	}

	@RequestMapping(value = "/setFile/{fileName}/{appName}", method = RequestMethod.GET)
	public void setFiles(@PathVariable("fileName") String fileName, @PathVariable String appName) throws IOException {
		mitekHelperService.setFileName(fileName, appName);
	}

	@RequestMapping(value = "/getFile", method = RequestMethod.GET)
	public List<String> getFile() throws IOException {
		return Collections.singletonList(mitekHelperService.getFileName());
	}

	@RequestMapping(value = "/response/mocked", method = RequestMethod.GET)
	public JsonNode getMockedResponse() throws IOException {
		return mitekHelperService.getAutoResult();
	}

	@RequestMapping(value = "/response/mocked/cache/{appName}", method = RequestMethod.GET)
	public JsonNode getMockedResponseFromCache(@PathVariable String appName) throws IOException {
		return mitekHelperService.getAutoResultFromCache(appName);
	}
}
