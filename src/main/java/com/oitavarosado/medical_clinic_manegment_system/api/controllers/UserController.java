package com.oitavarosado.medical_clinic_manegment_system.api.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oitavarosado.medical_clinic_manegment_system.api.dto.CreateUserDTO;
import com.oitavarosado.medical_clinic_manegment_system.api.dto.UserDTO;
import com.oitavarosado.medical_clinic_manegment_system.domain.entities.User;
import com.oitavarosado.medical_clinic_manegment_system.domain.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/user")
public class UserController {
	@Autowired
	private UserService service;
	@Autowired
	private ModelMapper mapper;
	
	@PostMapping
	public ResponseEntity<UserDTO> post(@Valid @RequestBody CreateUserDTO dto){
		User user = service.create(mapper.map(dto, User.class));
		if(user!=null) {
			UserDTO newDto = mapper.map(user, UserDTO.class);
			return new ResponseEntity<>(newDto,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
