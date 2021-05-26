package com.heinsohn.semillero.dto;

import java.io.Serializable;
import java.util.List;

import com.heinsohn.semillero.enums.RolesEnum;

public class UsuarioDTO implements Serializable {

	/**
	 * Atributo de serializacion
	 */
	private static final long serialVersionUID = 1L;

	private String idPublico;
	
	private int idUsuario;
	
	private String nombre;
	
	private String login;
	
	private String password;
	
	private List<RolesEnum> roles;
	
	public UsuarioDTO() {
		
	}
	
	public UsuarioDTO(String idPublico, int idUsuario, String nombre, String login, String password, List<RolesEnum> roles) {
		super();
		this.idPublico = idPublico;
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.login = login;
		this.password = password;
		this.roles = roles;
	}

	public String getIdPublico() {
		return idPublico;
	}

	public void setIdPublico(String idPublico) {
		this.idPublico = idPublico;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public List<RolesEnum> getRoles() {
		return roles;
	}

	public void setRoles(List<RolesEnum> roles) {
		this.roles = roles;
	}
	
}
