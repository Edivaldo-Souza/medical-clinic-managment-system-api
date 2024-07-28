package com.oitavarosado.medical_clinic_manegment_system.domain.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_doctors")
public class Doctor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(updatable=false, nullable=false, columnDefinition="VARCHAR(36)")
	private String uuid;
	@Column( nullable=false)
	private String nome;
	@Column( nullable=false)
	private String conselhoMedico; //Ex:CREMERN
	@Column( nullable=false)
	private String ufConselho;
	@Column( nullable=false)
	private String numeroConselho; //Ex:40
	@Column( nullable=false)
	private String cbo; //Ex:225125
	@Column(unique=true, nullable=false)
	private String cpf;
	@Column( nullable=false)
	private String logradouro;
	@Column( nullable=false)
	private String bairro;
	@Column( nullable=false)
	private String cidade;
	@Column( nullable=false)
	private String uf;
	@Column( nullable=false)
	private String cep;
	@Column(unique=true, nullable=false)
	private String telefone;
	@Column(unique=true, nullable=false)
	private String email;
	
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getConselhoMedico() {
		return conselhoMedico;
	}
	public void setConselhoMedico(String conselhoMedico) {
		this.conselhoMedico = conselhoMedico;
	}
	public String getUfConselho() {
		return ufConselho;
	}
	public void setUfConselho(String ufConselho) {
		this.ufConselho = ufConselho;
	}
	public String getNumeroConselho() {
		return numeroConselho;
	}
	public void setNumeroConselho(String numeroConselho) {
		this.numeroConselho = numeroConselho;
	}
	public String getCbo() {
		return cbo;
	}
	public void setCbo(String cbo) {
		this.cbo = cbo;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
