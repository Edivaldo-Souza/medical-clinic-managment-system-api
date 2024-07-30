package com.oitavarosado.medical_clinic_manegment_system.api.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AppointmentControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void create_shouldCreateSuccessfully() throws Exception {
		String requestBody = "{\r\n"
				+ "	\"medico\":{\r\n"
				+ "		\"uuid\":\"8ecec479-715b-473c-ab26-86e51160ff92\"\r\n"
				+ "	},\r\n"
				+ "	\"paciente\":{\r\n"
				+ "		\"uuid\":\"1aca19c2-df29-493f-ad95-f0a32db51bd8\"\r\n"
				+ "	},\r\n"
				+ "	\"motivoDaConsulta\":\"Desconforto constante nos olhos\",\r\n"
				+ "	\"dataConsulta\":{\r\n"
				+ "		\"data\":\"2024-07-29\",\r\n"
				+ "		\"horario\":\"19:22\"\r\n"
				+ "	},\r\n"
				+ "	\"localConsulta\":\"Clinica Oitava Rosado\",\r\n"
				+ "	\"obs\":\"Paciente com historico de espasmos\"\r\n"
				+ "}";
		
		mockMvc.perform(post("/api/appointment")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization","bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJlZGl2YWxkb3NvdXphIiwiZXhwIjoxNzIyNDI5OTk3fQ.Jx66xM1S_aITSB-vk56cnlY6TXFXwJS7aeLHi-Q9C3oywgzLdGN9hCEkoZpADvXWPnQIpWlp7fzsfSIkHaApag")
				.content(requestBody))
				.andExpect(status().isCreated());
	}
}
