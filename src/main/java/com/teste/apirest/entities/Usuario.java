package com.teste.apirest.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String mail;
	private String phone;
	private String password;
	
	//-- Relação das entidades
	//-- Só tem get
	//--Está apontando para o atributo "user" da classe Pedido
	//--Associação de mão dupla, gera loop
	//  para não acontecer se usa essa anotação 
	//  em qualquer um dos lados
	//--No pedido terá uma lazyLoading (Carrega tudo associado ao objeto referenciado)
	@JsonIgnore 
	@OneToMany(mappedBy = "user")
	private List<Pedido> order = new ArrayList<>();
	
	public Usuario() {
		
	}
	
	public Usuario(Long id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.mail = email;
		this.phone = phone;
		this.password = password;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String email) {
		this.mail = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Pedido> getOrder() {
		return order;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}
	
}
