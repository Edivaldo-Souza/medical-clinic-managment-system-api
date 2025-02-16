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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oitavarosado.medical_clinic_manegment_system.api.dto.DoctorDTO;
import com.oitavarosado.medical_clinic_manegment_system.domain.entities.Doctor;
import com.oitavarosado.medical_clinic_manegment_system.domain.services.DoctorService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/doctor")
public class DoctorController {
	@Autowired
	private DoctorService service;
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header")
	})
	public List<DoctorDTO> getAll(@RequestHeader(value = "Authorization") String authorization){
		List<DoctorDTO> doctors = new ArrayList<DoctorDTO>();
		for(Doctor p : service.getAll()) {
			doctors.add(mapper.map(p, DoctorDTO.class));
		}
		return doctors;
	}
	
	@GetMapping("/{id}")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header")
	})
	public ResponseEntity<DoctorDTO> get(
			@RequestHeader(value = "Authorization") String authorization, 
			@PathVariable UUID id){
		Doctor doctor = service.getAt(id);
		if(doctor!=null) {
			DoctorDTO newDto = mapper.map(doctor, DoctorDTO.class);
			return new ResponseEntity<>(newDto,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header")
	})
	public ResponseEntity<DoctorDTO> post(
			@RequestHeader(value = "Authorization") String authorization,
			@Valid @RequestBody DoctorDTO dto){
		Doctor doctor = service.create(mapper.map(dto, Doctor.class));
		if(doctor!=null) {
			DoctorDTO newDto = mapper.map(doctor, DoctorDTO.class);
			return new ResponseEntity<>(newDto,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header")
	})
	public ResponseEntity<DoctorDTO> put(
			@RequestHeader(value = "Authorization") String authorization,
			@Valid @RequestBody DoctorDTO dto){
		Doctor doctor = service.update(mapper.map(dto, Doctor.class));
		if(doctor!=null) {
			DoctorDTO newDto = mapper.map(doctor, DoctorDTO.class);
			return new ResponseEntity<>(newDto,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PatchMapping
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header")
	})
	public ResponseEntity<DoctorDTO> patch(
			@RequestHeader(value = "Authorization") String authorization,
			@RequestBody DoctorDTO dto){
		Doctor doctor = service.updatePatch(mapper.map(dto, Doctor.class));
		if(doctor!=null) {
			DoctorDTO newDto = mapper.map(doctor, DoctorDTO.class);
			return new ResponseEntity<>(newDto,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header")
	})
	public String delete(
			@RequestHeader(value = "Authorization") String authorization, 
			@PathVariable UUID id) {
		if(service.delete(id)) {
			return "Médico removido"; 
		}
		else return "Médico não encontrado"; 
	}
}
