package com.oitavarosado.medical_clinic_manegment_system.domain.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oitavarosado.medical_clinic_manegment_system.domain.entities.Doctor;
import com.oitavarosado.medical_clinic_manegment_system.domain.repository.DoctorRepository;
import com.oitavarosado.medical_clinic_manegment_system.helpers.NullAwareBeanUtilsBean;

@Service
public class DoctorService implements ServiceInterface<Doctor>{
	@Autowired
	DoctorRepository repository;
	@Autowired
	NullAwareBeanUtilsBean beanUtilsBean;
	
	@Override
	public List<Doctor> getAll() {
		return repository.findAll();
	}

	@Override
	public Doctor getAt(UUID id) {
		return repository.findByUuid(id.toString());
	}

	@Override
	public Doctor create(Doctor obj) {
		
		obj.setUuid(UUID.randomUUID().toString());
		
		return repository.save(obj);
	}

	@Override
	public Doctor update(Doctor obj) {
		Doctor doctor = repository.findByUuid(obj.getUuid());
		
		doctor.setLogradouro(obj.getLogradouro());
		doctor.setBairro(obj.getBairro());
		doctor.setCep(obj.getCep());
		doctor.setCidade(obj.getCidade());
		doctor.setUf(obj.getUf());
		doctor.setTelefone(obj.getTelefone());
		doctor.setEmail(obj.getEmail());
		
		return repository.save(doctor);
	}

	@Override
	public Doctor updatePatch(Doctor obj) {
		Doctor doctor = repository.findByUuid(obj.getUuid());
		try {
			beanUtilsBean.copyProperties(doctor, obj);
			
			return repository.save(doctor);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(UUID id) {
		Doctor doctor = repository.findByUuid(id.toString());
		
		if(doctor==null) return false;
		
		repository.delete(doctor);
		return true;
	}

}
