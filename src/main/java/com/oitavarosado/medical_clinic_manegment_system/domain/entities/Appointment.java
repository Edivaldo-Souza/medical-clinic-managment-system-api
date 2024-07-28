package com.oitavarosado.medical_clinic_manegment_system.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="tb_appointments", uniqueConstraints= {
		@UniqueConstraint(columnNames = {"data","horario"})
})
public class Appointment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Embedded
	private AppointmentDate dataConsulta;
	@Column(unique=true, nullable=false)
	private String uuid;
	@ManyToOne
	@JoinColumn(name="medico_id")
	private Doctor medico;
	@ManyToOne
	@JoinColumn(name="paciente_id")
	private Patient paciente;
	@Column(nullable=false)
	private String motivoDaConsulta;
	@Column(nullable=false)
	private String localConsulta;
	private String obs;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Doctor getMedico() {
		return medico;
	}
	public void setMedico(Doctor medico) {
		this.medico = medico;
	}
	public Patient getPaciente() {
		return paciente;
	}
	public void setPaciente(Patient paciente) {
		this.paciente = paciente;
	}
	public String getMotivoDaConsulta() {
		return motivoDaConsulta;
	}
	public void setMotivoDaConsulta(String motivoDaConsulta) {
		this.motivoDaConsulta = motivoDaConsulta;
	}
	public AppointmentDate getDataConsulta() {
		return dataConsulta;
	}
	public void setDataConsulta(AppointmentDate dataConsulta) {
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
