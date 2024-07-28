package com.oitavarosado.medical_clinic_manegment_system.domain.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.oitavarosado.medical_clinic_manegment_system.domain.entities.User;
import com.oitavarosado.medical_clinic_manegment_system.domain.repository.UserRepository;

@Service
public class UserService implements ServiceInterface<User>{
	@Autowired
	UserRepository repository;

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getAt(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User create(User obj) {
		String encryptedPassword = BCrypt.hashpw(obj.getSenha(), BCrypt.gensalt());
		
		obj.setSenha(encryptedPassword);
		
		obj.setUuid(UUID.randomUUID().toString());
		
		return repository.save(obj);
	}

	@Override
	public User update(User obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updatePatch(User obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(UUID id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
