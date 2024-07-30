package com.oitavarosado.medical_clinic_manegment_system.api.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.oitavarosado.medical_clinic_manegment_system.domain.services.AuthenticationService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class AuthorizationFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Authentication auth = AuthenticationService.getAuthentication((HttpServletRequest)request);
		SecurityContextHolder.getContext().setAuthentication(auth);
		String path = ((HttpServletRequest)request).getRequestURI().toString();
		
		
		List<String> pathList = new ArrayList<>();
		pathList.add("/swagger-ui");
		pathList.add("/error");
		pathList.add("/favicon.ico");
		
	    if (pathList.stream().filter(it -> path.startsWith(it)).toList().size() > 0) {
	    	chain.doFilter(request, response);
	    }
		chain.doFilter(request, response);
		
	}
	
}
