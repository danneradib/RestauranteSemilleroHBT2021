package com.heinsohn.semillero.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.heinsohn.semillero.enums.EstadosEnum;
import com.heinsohn.semillero.enums.RolesEnum;

@Entity
@Table(name = "ROL")
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private int id;
	
	@Column(name = "NOMBRE", nullable = false)
	@Size(max = 30, min = 5)
	@Enumerated(EnumType.STRING)
	private RolesEnum nombre;
	
	@Column(name = "ESTADO", length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private EstadosEnum estado;
	
	@ManyToMany(mappedBy = "roles")
	private Set<Usuarios> usuarios;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
	private Set<Menu> menus;

	public Rol() {
		
	}
	
	public Rol(int id, @Size(max = 30, min = 5) RolesEnum nombre, EstadosEnum estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RolesEnum getNombre() {
		return nombre;
	}

	public void setNombre(RolesEnum nombre) {
		this.nombre = nombre;
	}

	public EstadosEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadosEnum estado) {
		this.estado = estado;
	}

	public Set<Usuarios> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuarios> usuarios) {
		this.usuarios = usuarios;
	}

	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
	
}
