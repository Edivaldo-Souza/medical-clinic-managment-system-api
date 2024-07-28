package com.oitavarosado.medical_clinic_manegment_system.api.dto;

import java.time.LocalDate;

public class AppointmentDateDTO {
	private LocalDate data;
	private String horario;
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	
}
