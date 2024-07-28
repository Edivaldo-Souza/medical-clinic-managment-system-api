package com.oitavarosado.medical_clinic_manegment_system.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oitavarosado.medical_clinic_manegment_system.domain.entities.Appointment;
import com.oitavarosado.medical_clinic_manegment_system.domain.entities.AppointmentDate;

public interface AppointmentRepository extends JpaRepository<Appointment,Long>{
	Appointment findByUuid(String uuid);
	boolean existsByDataConsulta(AppointmentDate dataConsulta);
}
