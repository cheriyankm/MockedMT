package com.mock.mitek.idv.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class IdvFilter implements Filter {

	private final static String MOCK_API = "/mockit";

	private final static String MOCK_CONTROLLER_API = "/mockitcontroller";

	private final static String MOCKED_API_ATTRIBUTE_KEY = "abcd";

	private static final String APP_NAME = "appName";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		String uri = httpServletRequest.getRequestURI();
		Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();

		if (uri.contains(MOCK_API) && uri.length() > MOCK_API.length()) {
			String appName = uri.substring(MOCK_API.length()).split("/")[1];
			httpServletRequest.setAttribute(MOCKED_API_ATTRIBUTE_KEY,
					uri.substring(MOCK_API.length() + appName.length() + 1));
			httpServletRequest.setAttribute(APP_NAME, appName);
		}

		if (MOCK_API.equalsIgnoreCase(uri) || uri.contains(MOCK_API)) {
			// httpServletResponse.sendRedirect("/mockit");
			httpServletRequest.getRequestDispatcher(MOCK_CONTROLLER_API).forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}
}
