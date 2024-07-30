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

import com.oitavarosado.medical_clinic_manegment_system.api.dto.AppointmentDTO;
import com.oitavarosado.medical_clinic_manegment_system.domain.entities.Appointment;
import com.oitavarosado.medical_clinic_manegment_system.domain.services.AppointmentService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/appointment")
public class AppointmentController {
	@Autowired
	private AppointmentService service;
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header")
	})
	public List<AppointmentDTO> getAll(@RequestHeader(value = "Authorization") String authorization){
		List<AppointmentDTO> patients = new ArrayList<AppointmentDTO>();
		for(Appointment p : service.getAll()) {
			patients.add(mapper.map(p, AppointmentDTO.class));
		}
		return patients;
	}
	
	@GetMapping("/{id}")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header")
	})
	public ResponseEntity<AppointmentDTO> get(
			@RequestHeader(value = "Authorization") String authorization,
			@PathVariable UUID id){
		Appointment patient = service.getAt(id);
		if(patient!=null) {
			AppointmentDTO newDto = mapper.map(patient, AppointmentDTO.class);
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
	public ResponseEntity<AppointmentDTO> post(
			@RequestHeader(value = "Authorization") String authorization,
			@Valid @RequestBody AppointmentDTO dto){
		Appointment appointment = service.create(
				mapper.map(dto, Appointment.class),
				dto.getPaciente().getUuid(),
				dto.getMedico().getUuid());

		if(appointment!=null) {
			AppointmentDTO newDto = mapper.map(appointment, AppointmentDTO.class);
			
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
	public ResponseEntity<AppointmentDTO> put(
			@RequestHeader(value = "Authorization") String authorization,
			@Valid @RequestBody AppointmentDTO dto){
		Appointment patient = service.update(mapper.map(dto, Appointment.class));
		if(patient!=null) {
			AppointmentDTO newDto = mapper.map(patient, AppointmentDTO.class);
			
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
	public ResponseEntity<AppointmentDTO> patch(
			@RequestHeader(value = "Authorization") String authorization,
			@RequestBody AppointmentDTO dto){
		Appointment patient = service.updatePatch(mapper.map(dto, Appointment.class));
		if(patient!=null) {
			AppointmentDTO newDto = mapper.map(patient, AppointmentDTO.class);
			
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
			return "Agendamento removido"; 
		}
		else return "Agendamento não encontrado"; 
	}
	
}

