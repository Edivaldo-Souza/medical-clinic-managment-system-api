package com.oitavarosado.medical_clinic_manegment_system.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oitavarosado.medical_clinic_manegment_system.domain.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient,Long>{
	Patient findByUuid(String uuid);
}
