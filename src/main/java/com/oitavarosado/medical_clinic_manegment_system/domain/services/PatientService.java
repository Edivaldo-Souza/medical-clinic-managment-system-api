package com.oitavarosado.medical_clinic_manegment_system.domain.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oitavarosado.medical_clinic_manegment_system.domain.entities.Patient;
import com.oitavarosado.medical_clinic_manegment_system.domain.repository.PatientRepository;
import com.oitavarosado.medical_clinic_manegment_system.helpers.NullAwareBeanUtilsBean;

@Service
public class PatientService implements ServiceInterface<Patient>{
	@Autowired
	PatientRepository repository;
	@Autowired
	NullAwareBeanUtilsBean beanUtilsBean;

	
	@Override
	public List<Patient> getAll() {
		return repository.findAll();
	}

	@Override
	public Patient getAt(UUID id) {
		return repository.findByUuid(id.toString());
	}

	@Override
	public Patient create(Patient obj) {
		
		obj.setUuid(UUID.randomUUID().toString());
		
		return repository.save(obj);
	}

	@Override
	public Patient update(Patient obj) {
		Patient patient = repository.findByUuid(obj.getUuid());
		
		patient.setLogradouro(obj.getLogradouro());
		patient.setBairro(obj.getBairro());
		patient.setCep(obj.getCep());
		patient.setCidade(obj.getCidade());
		patient.setUf(obj.getUf());
		patient.setObs(obj.getObs());
		patient.setTelefone(obj.getTelefone());
		patient.setEmail(obj.getEmail());
		
		return repository.save(patient);
	}

	@Override
	public Patient updatePatch(Patient obj) {
		Patient patient = repository.findByUuid(obj.getUuid());
		
		try {
			beanUtilsBean.copyProperties(patient, obj);
			
			return repository.save(patient);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(UUID id) {
		Patient patient = repository.findByUuid(id.toString());
		
		if(patient==null) return false;
		
		repository.delete(patient);
		return true;
	}

}
