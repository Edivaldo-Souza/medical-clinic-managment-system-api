package com.oitavarosado.medical_clinic_manegment_system.api.filters;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oitavarosado.medical_clinic_manegment_system.domain.entities.User;
import com.oitavarosado.medical_clinic_manegment_system.domain.services.AuthenticationService;

public class LoginFilter extends AbstractAuthenticationProcessingFilter{
	public LoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		User user = new ObjectMapper().readValue(request.getInputStream(),User.class);
		
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(user.getNome(),user.getSenha(),Collections.emptyList()));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req,HttpServletResponse res, FilterChain chain, Authentication auth)
	throws IOException, ServletException{
		AuthenticationService.addToken(res,auth.getName());
		res.setHeader("Access-Control-Expose-Headers", "Authorization");
	}

}
