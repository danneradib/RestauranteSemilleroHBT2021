package com.heinsohn.semillero.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.heinsohn.semillero.dto.UsuarioDTO;

/**
 * Interface que permite exponer metodos para persistir la informacion del uasurio
 * 
 * @author Danner Uribe
 *
 */
public interface UsuariosService extends UserDetailsService {

	/**
	 * Este metodo permite persistir un usuario con la 
	 * informacion en la base de datos
	 * 
	 * @param usuario
	 * @return UsuarioDTO
	 */
	UsuarioDTO crearUsuario(UsuarioDTO usuario);
	
	/**
	 * Este metodo permite consultar la informacion de los usuarios
	 * 
	 * @return List<UsuarioDTO>
	 */
	List<UsuarioDTO> consultarUsuarios();
	
	/**
	 * Este metodo permite eliminar la infromacion de un usuario filtrando por el id
	 * 
	 * @param id
	 * @return boolean, true si se elimina, false en caso contrario
	 */
	boolean eliminarUsuarioXId(int id);
	
	/**
	 * Este metodo permite actualizar la informacion del usuario
	 * 
	 * @param usuario
	 * @return UsuarioDTO
	 */
	UsuarioDTO actualizarUsuario(UsuarioDTO usuario);
	
	/**
	 * Este metodo permite consultar la informacion del usaurio por el login
	 * 
	 * @param login
	 * @return UsuarioDTO
	 */
	UsuarioDTO consultarUsuarioXLogin(String login);
	
	/**
	 * Este metodo permite consultar la infromacion del usuario
	 * filtrando por el login
	 * 
	 * @param login
	 * @return UsuarioDTO
	 */
	UsuarioDTO getUsetDateilsByUserLogin(String login);
	
}
