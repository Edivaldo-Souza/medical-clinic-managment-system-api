package com.oitavarosado.medical_clinic_manegment_system.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oitavarosado.medical_clinic_manegment_system.api.dto.PatientDTO;
import com.oitavarosado.medical_clinic_manegment_system.domain.entities.Patient;
import com.oitavarosado.medical_clinic_manegment_system.domain.services.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/patient")
public class PatientController {
	@Autowired
	private PatientService service;
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	public List<PatientDTO> getAll(){
		List<PatientDTO> patients = new ArrayList<PatientDTO>();
		for(Patient p : service.getAll()) {
			patients.add(mapper.map(p, PatientDTO.class));
		}
		return patients;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PatientDTO> get(@PathVariable UUID id){
		Patient patient = service.getAt(id);
		if(patient!=null) {
			PatientDTO newDto = mapper.map(patient, PatientDTO.class);
			return new ResponseEntity<>(newDto,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<PatientDTO> post(@Valid @RequestBody PatientDTO dto){
		Patient patient = service.create(mapper.map(dto, Patient.class));
		if(patient!=null) {
			PatientDTO newDto = mapper.map(patient, PatientDTO.class);
			return new ResponseEntity<>(newDto,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping
	public ResponseEntity<PatientDTO> put(@Valid @RequestBody PatientDTO dto){
		Patient patient = service.update(mapper.map(dto, Patient.class));
		if(patient!=null) {
			PatientDTO newDto = mapper.map(patient, PatientDTO.class);
			return new ResponseEntity<>(newDto,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PatchMapping
	public ResponseEntity<PatientDTO> patch(@RequestBody PatientDTO dto){
		Patient patient = service.updatePatch(mapper.map(dto, Patient.class));
		if(patient!=null) {
			PatientDTO newDto = mapper.map(patient, PatientDTO.class);
			return new ResponseEntity<>(newDto,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable UUID id) {
		if(service.delete(id)) {
			return "Paciente removido"; 
		}
		else return "Paciente não encontrado"; 
	}
	
}
