package com.oitavarosado.medical_clinic_manegment_system.api.dto;

public class AppointmentDTO {
	private String uuid;
	private DoctorDTO medico;
	private PatientDTO paciente;
	private String motivoDaConsulta;
	private AppointmentDateDTO dataConsulta;
	private String localConsulta;
	private String obs;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public DoctorDTO getMedico() {
		return medico;
	}
	public void setMedico(DoctorDTO medico) {
		this.medico = medico;
	}
	public PatientDTO getPaciente() {
		return paciente;
	}
	public void setPaciente(PatientDTO paciente) {
		this.paciente = paciente;
	}
	public String getMotivoDaConsulta() {
		return motivoDaConsulta;
	}
	public void setMotivoDaConsulta(String motivoDaConsulta) {
		this.motivoDaConsulta = motivoDaConsulta;
	}
	public AppointmentDateDTO getDataConsulta() {
		return dataConsulta;
	}
	public void setDataConsulta(AppointmentDateDTO dataConsulta) {
		this.dataConsulta = dataConsulta;
	}
	public String getLocalConsulta() {
		return localConsulta;
	}
	public void setLocalConsulta(String localConsulta) {
		this.localConsulta = localConsulta;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	
}
