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
	
}
