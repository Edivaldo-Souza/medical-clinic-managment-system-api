package com.oitavarosado.medical_clinic_manegment_system.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oitavarosado.medical_clinic_manegment_system.domain.entities.User;
import com.oitavarosado.medical_clinic_manegment_system.domain.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User currentUser = repository.findByNome(username);
		UserDetails userDetails = new org.springframework.security.core.userdetails.
				User(username, currentUser.getSenha(), AuthorityUtils.createAuthorityList("USER"));
		return userDetails;
	}
	
	
}
