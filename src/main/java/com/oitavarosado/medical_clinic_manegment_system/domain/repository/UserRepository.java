package com.oitavarosado.medical_clinic_manegment_system.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oitavarosado.medical_clinic_manegment_system.domain.entities.User;

public interface UserRepository extends JpaRepository<User,Long>{
	User findByNome(String name);
	User findByEmail(String email);
	User findByResetToken(String resetToken);
}
