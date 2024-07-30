package com.oitavarosado.medical_clinic_manegment_system.domain.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
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
	
	public String savePasswordResetToken(String email) {
        
		Random random = new Random();
		User user = repository.findByEmail(email);
		LocalDateTime now = LocalDateTime.now();
		
		if (user != null) {
			
			String token = Integer.toString(random.nextInt(100000));
	        user.setResetToken(token);
	        user.setResetTokenCreatedAt(now);
	        user.setResetTokenExpiresAt(now.plusMinutes(30));
  
            user.setResetToken(token);
            repository.save(user);
            return token;
        }
		return null;
    }
	
	public boolean verifyToken(String token) {
		
		User user = repository.findByResetToken(token);
        
		if (user.getResetTokenExpiresAt().isAfter(LocalDateTime.now())) {
			return true;
        }
		return false;
	}
	
	public User saveNewPassword(User obj) {
		
		String encryptedPassword = BCrypt.hashpw(obj.getSenha(), BCrypt.gensalt());
		
		User user = repository.findByEmail(obj.getEmail());
        
		if (user != null) {
            user.setSenha(encryptedPassword);
            
            return repository.save(user);
        }
		return null;
	}
	
}
