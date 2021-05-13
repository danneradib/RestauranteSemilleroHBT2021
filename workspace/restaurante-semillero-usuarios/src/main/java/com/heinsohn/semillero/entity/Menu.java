package com.heinsohn.semillero.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.heinsohn.semillero.emuns.EstadosEnum;

@Entity
@Table(name = "MENU")
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private int id;
	
	@Size(max = 200, min = 10)
	@Column(name = "OPCION", nullable = false)
	private String opcion;
	
	@Size(max = 200, min = 10)
	@Column(name = "URL", nullable = true)
	private String url;
	
	@Size(max = 100, min = 10)
	@Column(name = "ICONO", nullable = true)
	private String icono;
	
	@Column(name = "ESTADO", length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private EstadosEnum estado;
	
	@Column(name = "ID_PADRE", nullable = true)
	private int idPadre;
	
	@ManyToMany
	@JoinTable(
	  name = "MENU_ROL", 
	  joinColumns = @JoinColumn(name = "ID_MENU"), 
	  inverseJoinColumns = @JoinColumn(name = "ID_ROL"))
	private Set<Rol> roles;

	public Menu() {
		
	}
	
	public Menu(int id, @Size(max = 200, min = 10) String opcion, @Size(max = 200, min = 10) String url,
			@Size(max = 100, min = 10) String icono, EstadosEnum estado, int idPadre) {
		super();
		this.id = id;
		this.opcion = opcion;
		this.url = url;
		this.icono = icono;
		this.estado = estado;
		this.idPadre = idPadre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public EstadosEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadosEnum estado) {
		this.estado = estado;
	}

	public int getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(int idPadre) {
		this.idPadre = idPadre;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	
	
}
