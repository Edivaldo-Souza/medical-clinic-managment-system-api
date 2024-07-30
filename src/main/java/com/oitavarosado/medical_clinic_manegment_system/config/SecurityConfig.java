package com.oitavarosado.medical_clinic_manegment_system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.oitavarosado.medical_clinic_manegment_system.api.filters.AuthorizationFilter;
import com.oitavarosado.medical_clinic_manegment_system.api.filters.LoginFilter;
import com.oitavarosado.medical_clinic_manegment_system.domain.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled=true)
public class SecurityConfig {
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception{
		
		AuthenticationManagerBuilder authManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		
		authManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		
		AuthenticationManager authManager = authManagerBuilder.build();
		
		http
		.cors((cors)->cors.disable())
		.csrf((crsf)->crsf.disable())
		.authorizeHttpRequests((requests)->{
			
			requests.requestMatchers(HttpMethod.POST,
					"api/user",
					"api/password/send",
					"api/password/verify",
					"api/password/reset")
					.permitAll()
					.requestMatchers(HttpMethod.GET,
					"/swagger-ui/**",
					"/swagger-ui.html",
					"/v3/**",
					"/favicon.ico")
					.permitAll()
					.anyRequest().authenticated();
			
		
		})
		.addFilterBefore(new LoginFilter("/api/login",authManager),UsernamePasswordAuthenticationFilter.class)
		.addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
		.authenticationManager(authManager);
	
		
		return http.build();
	}
}
