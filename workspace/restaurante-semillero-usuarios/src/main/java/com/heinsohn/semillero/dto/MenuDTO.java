package com.heinsohn.semillero.dto;

import java.io.Serializable;
import java.util.List;

public class MenuDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String opcion;
	
	private String url;
	
	private String icono;
	
	private List<MenuDTO> hijos;

	public MenuDTO() {
		
	}
	
	public MenuDTO(String opcion, String url, String icono, List<MenuDTO> hijos) {
		super();
		this.opcion = opcion;
		this.url = url;
		this.icono = icono;
		this.hijos = hijos;
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

	public List<MenuDTO> getHijos() {
		return hijos;
	}

	public void setHijos(List<MenuDTO> hijos) {
		this.hijos = hijos;
	}
	
}
