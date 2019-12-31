package com.mock.mitek.idv.services;

import java.util.Arrays;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public class DemoService {

	public void insert() {
		System.out.println("insert");
	}

	public void select() {
		System.out.println("select");
	}

	public void delete() {
		System.out.println("delete");
	}

	public String mockIt(HttpServletRequest request) {
		System.out.println("inside service***********");
		System.out.println("Mocked API - "+request.getAttribute("abcd"));
		
		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + Arrays.toString(entry.getValue()));
		}
		System.out.println("================================================");
		return "Successs";
	}

	
}
