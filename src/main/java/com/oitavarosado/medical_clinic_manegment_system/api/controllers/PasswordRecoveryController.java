package com.oitavarosado.medical_clinic_manegment_system.api.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oitavarosado.medical_clinic_manegment_system.api.dto.EmailDTO;
import com.oitavarosado.medical_clinic_manegment_system.api.dto.ModifiedUserDTO;
import com.oitavarosado.medical_clinic_manegment_system.api.dto.TokenDTO;
import com.oitavarosado.medical_clinic_manegment_system.domain.entities.User;
import com.oitavarosado.medical_clinic_manegment_system.domain.services.EmailService;
import com.oitavarosado.medical_clinic_manegment_system.domain.services.UserService;

import jakarta.mail.MessagingException;

@RestController	
@RequestMapping("api/password")
public class PasswordRecoveryController {
	 @Autowired
	 private EmailService emailService;
	 
	 @Autowired
	 private UserService userService;
	 
	 @Autowired
	 private ModelMapper mapper;
	 
	 @PostMapping("/send")
	 public String sendConfirmationCode(@RequestBody EmailDTO dto) {

		 String token = userService.savePasswordResetToken(dto.getEmail());
		 
		 if(token!=null) {
			 String subject = "Recuperação de Senha";
			 String text = "Código de Confirmação: " + token;

			 try {
				 emailService.sendEmail(dto.getEmail(), subject, text);
			 } catch (MessagingException e) {
				 e.printStackTrace();
			 }

			 return "Email de recuperação enviado.";
		 }
		 return "Usuário não encontrado";

	 }
	 @PostMapping("/verify")
	 public  ResponseEntity<String> verifyToken(@RequestBody TokenDTO dto) {
		 
		 if(userService.verifyToken(dto.getResetToken())) {
			 return new ResponseEntity<>("Token válido",HttpStatus.OK);
		 }
		 return new ResponseEntity<>("Token inválido",HttpStatus.NOT_FOUND);
		 
	 }
	 
	 
	 @PostMapping("/reset")
	 public ResponseEntity<String> resetPassword(@RequestBody ModifiedUserDTO dto) {
		 
		 if(userService.verifyToken(dto.getResetToken())) {
			 User user = userService.saveNewPassword(mapper.map(dto, User.class));
			 if(user!=null) {
				 return new ResponseEntity<>("Senha redefinida",HttpStatus.OK);
			 } 
		 }
		 return new ResponseEntity<>("Token inválido",HttpStatus.NOT_FOUND);
	 }
}
