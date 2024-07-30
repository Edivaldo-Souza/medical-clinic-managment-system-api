package com.oitavarosado.medical_clinic_manegment_system.domain.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_users")
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(updatable=false, nullable=false, columnDefinition="VARCHAR(36)")
	private String uuid;
	@Column(unique=true, nullable=false)
	private String nome;
	@Column( nullable=false)
	private String senha;
	@Column(unique=true, nullable=false)
	private String email;
	private String resetToken;
	private LocalDateTime resetTokenCreatedAt;
	private LocalDateTime resetTokenExpiresAt;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getResetToken() {
		return resetToken;
	}
	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}
	public LocalDateTime getResetTokenCreatedAt() {
		return resetTokenCreatedAt;
	}
	public void setResetTokenCreatedAt(LocalDateTime resetTokenCreatedAt) {
		this.resetTokenCreatedAt = resetTokenCreatedAt;
	}
	public LocalDateTime getResetTokenExpiresAt() {
		return resetTokenExpiresAt;
	}
	public void setResetTokenExpiresAt(LocalDateTime resetTokenExpiresAt) {
		this.resetTokenExpiresAt = resetTokenExpiresAt;
	}
	
}
