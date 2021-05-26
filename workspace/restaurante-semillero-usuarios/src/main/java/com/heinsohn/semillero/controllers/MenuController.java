package com.heinsohn.semillero.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.*;

import javax.annotation.security.RolesAllowed;

import com.heinsohn.semillero.dto.MenuDTO;
import com.heinsohn.semillero.entity.Menu;
import com.heinsohn.semillero.entity.Rol;
import com.heinsohn.semillero.entity.Usuarios;
import com.heinsohn.semillero.repository.UsuariosJpaRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/menu")
public class MenuController {
	
	private UsuariosJpaRepository uJPARepository;
	
	public MenuController() {

	}
	
	
	@ApiOperation(value = "Metodo que retorna las opciones del menu del usaurio por medio del metodo HTTP.GET",
			  response = Integer.class, tags = "getMenu")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Exitoso, funcion!"),
		@ApiResponse(code = 401, message = "No autorizado, pilas!"),
		@ApiResponse(code = 403, message = "No tienes permiso!"),
		@ApiResponse(code = 404, message = "No se encontro lo que buscabas!")
	})
	@RolesAllowed({"ADMINISTRADOR", "CHEFF", "CLIENTE"})
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
	public List<MenuDTO> getMenu(Principal principal){
		List<MenuDTO> menus = new ArrayList<>();
		Authentication authentication = (Authentication) principal;
		User user = (User) authentication.getPrincipal();
		Usuarios u = uJPARepository.findByIdPublico("1");
		if(null != u) {
			ModelMapper modelMapper = new ModelMapper();
			for(Rol r : u.getRoles()) {
				for(Menu m : r.getMenus()) {
					menus.add(modelMapper.map(m, MenuDTO.class));
				}
			}
		}
		return null;
	}

}
