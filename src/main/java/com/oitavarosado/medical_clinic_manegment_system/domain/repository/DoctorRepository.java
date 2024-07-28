package com.oitavarosado.medical_clinic_manegment_system.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oitavarosado.medical_clinic_manegment_system.domain.entities.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Long>{
	Doctor findByUuid(String uuid);
}
