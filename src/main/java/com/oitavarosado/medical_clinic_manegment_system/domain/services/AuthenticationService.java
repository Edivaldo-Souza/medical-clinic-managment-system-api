package com.oitavarosado.medical_clinic_manegment_system.domain.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationService {
	static final long EXPIRATIONTIME = 864_000_00;
	static final String SIGNINKEY = "to_all_those_who_cared";
	static final String PREFIX = "bearer";
	
	static public void addToken(HttpServletResponse res, String name) {
		String jwtToken; 
		JwtBuilder builder = Jwts.builder().setSubject(name);
		builder.setExpiration(new Date(System.currentTimeMillis()+EXPIRATIONTIME));
		builder.signWith(SignatureAlgorithm.HS512, SIGNINKEY);
		
		jwtToken = builder.compact();
		res.addHeader("Authorization", PREFIX+" "+jwtToken);
		res.addHeader("Acess-Control-Expose-Headers", "Authorization");
		
	}
	
	static public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token!=null) {
			JwtParser parser = Jwts.parser();
			parser.setSigningKey(SIGNINKEY);
			String name = parser.parseClaimsJws(token.replace(PREFIX, "")).getBody().getSubject();
			
			if(name!=null) {
				return new UsernamePasswordAuthenticationToken(name,null,Collections.emptyList());
				
			}
		}
		return null;
	}
}
