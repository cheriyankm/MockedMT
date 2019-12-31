package com.mock.mitek.idv.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.core.ApplicationFilterChain;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class IdvFilter implements Filter {
	
	final static String MOCK_API = "/mockit";
	
	final static String MOCK_CONTROLLER_API = "/mockitcontroller";
	
	final static String MOCKED_API_ATTRIBUTE_KEY = "abcd";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		String uri = httpServletRequest.getRequestURI();
		Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
		System.out.println("API : " + uri);

		httpServletRequest.setAttribute(MOCKED_API_ATTRIBUTE_KEY, uri.substring(MOCK_API.length()));
		System.out.println("attr abcd : " + httpServletRequest.getAttribute(MOCKED_API_ATTRIBUTE_KEY));

		for (Entry<String, String[]> entry : parameterMap.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + Arrays.toString(entry.getValue()));
		}
		
		if (MOCK_API.equalsIgnoreCase(uri) || uri.contains(MOCK_API)) {
			//httpServletResponse.sendRedirect("/mockit");
			httpServletRequest.getRequestDispatcher(MOCK_CONTROLLER_API).forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}
}
