package com.oitavarosado.medical_clinic_manegment_system.domain.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.oitavarosado.medical_clinic_manegment_system.domain.entities.Appointment;
import com.oitavarosado.medical_clinic_manegment_system.domain.repository.AppointmentRepository;
import com.oitavarosado.medical_clinic_manegment_system.domain.repository.DoctorRepository;
import com.oitavarosado.medical_clinic_manegment_system.domain.repository.PatientRepository;
import com.oitavarosado.medical_clinic_manegment_system.helpers.NullAwareBeanUtilsBean;

@Service
public class AppointmentService implements ServiceInterface<Appointment>{
	@Autowired
	AppointmentRepository repository;
	@Autowired
	PatientRepository patientRepository;
	@Autowired
	DoctorRepository doctorRepository;
	@Autowired
	NullAwareBeanUtilsBean beanUtilsBean;
	
	@Override
	public List<Appointment> getAll() {
		return repository.findAll();
	}

	@Override
	public Appointment getAt(UUID id) {
		return repository.findByUuid(id.toString());
	}

	@Override
	public Appointment create(Appointment obj) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Appointment create(Appointment obj, String patientUuid, String doctorUuid) {
		
		if(repository.existsByDataConsulta(obj.getDataConsulta())) {
			throw new DataIntegrityViolationException("Horario ja reservado");
		}
		obj.setUuid(UUID.randomUUID().toString());
		obj.setMedico(doctorRepository.findByUuid(doctorUuid));
		obj.setPaciente(patientRepository.findByUuid(patientUuid));
		
		return repository.save(obj);
	}

	@Override
	public Appointment update(Appointment obj) {
	
		Appointment appointment = repository.findByUuid(obj.getUuid());
		
		appointment.setDataConsulta(obj.getDataConsulta());
		appointment.setLocalConsulta(obj.getLocalConsulta());
		appointment.setMotivoDaConsulta(obj.getMotivoDaConsulta());
		appointment.setObs(obj.getObs());
		
		return repository.save(appointment);
	}

	@Override
	public Appointment updatePatch(Appointment obj) {
		Appointment appointment = repository.findByUuid(obj.getUuid());
		
		try {
			beanUtilsBean.copyProperties(appointment, obj);
			
			return repository.save(appointment);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(UUID id) {
		Appointment appointment = repository.findByUuid(id.toString());
		
		if(appointment==null) return false;
		
		repository.delete(appointment);
		return true;
	}

}
