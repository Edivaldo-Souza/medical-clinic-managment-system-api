package com.oitavarosado.medical_clinic_manegment_system;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MedicalClinicManegmentSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalClinicManegmentSystemApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		
		return new ModelMapper();
	}
}
