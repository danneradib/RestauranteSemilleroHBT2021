package com.heinsohn.semillero.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.heinsohn.semillero.emuns.EstadosEnum;

@Entity
@Table(name = "USUARIOS")
public class Usuarios {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private int id;
	
	@Column(name = "NOMBRE", length = 100, nullable = false)
	@Size(max = 100, min = 10)
	private String nombre;
	
	@Column(name = "LOGIN", length = 150, nullable = false)
	@Size(max = 150, min = 10)
	private String login;

	@Column(name = "PASSWORD", length = 150, nullable = false)
	@Size(max = 150, min = 10)
	private String password;
	
	@ManyToMany
	@JoinTable(
	  name = "USURIO_ROL", 
	  joinColumns = @JoinColumn(name = "ID_USUARIO"), 
	  inverseJoinColumns = @JoinColumn(name = "ID_ROL"))
	private Set<Rol> roles;
	
	@Column(name = "ESTADO", length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private EstadosEnum estado;
	
	public Usuarios() {
		
	}
	
	public Usuarios(int id, String nombre, String login, String password, EstadosEnum estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.login = login;
		this.password = password;
		this.estado = estado;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EstadosEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadosEnum estado) {
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	
}
