package com.heinsohn.semillero.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.heinsohn.semillero.dto.UsuarioDTO;
import com.heinsohn.semillero.entity.Usuarios;
import com.heinsohn.semillero.enums.EstadosEnum;
import com.heinsohn.semillero.repository.UsuariosJpaRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/usuarios")
public class UserController {

	/**
	 * Atributo que permite ejecutar los metodos de persistencia
	 */
	private UsuariosJpaRepository uJpaRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/**
	 * Metodo constructor con los atributos que se inicialiaran
	 * 
	 */
	public UserController(UsuariosJpaRepository uJpaRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.uJpaRepository = uJpaRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	/**
	 * Metodo que retorna los datos de los usuarios
	 * por medio del metodo HTTP.GET
	 * 
	 * @return String
	 */
	@ApiOperation(value = "Metodo que retorna los datos de los usuarios por medio del metodo HTTP.GET",
				  response = Integer.class, tags = "consultarUsuarios")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Exitoso, funcion!"),
		@ApiResponse(code = 401, message = "No autorizado, pilas!"),
		@ApiResponse(code = 403, message = "No tienes permiso!"),
		@ApiResponse(code = 404, message = "No se encontro lo que buscabas!")
	})
	@GetMapping(//consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
				produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
	public List<UsuarioDTO> getUsuario() {
		List<Usuarios> usuarios = uJpaRepository.findAll();
		
		if(null != usuarios && !usuarios.isEmpty()) {
			ModelMapper modelMapper = new ModelMapper();

			List<UsuarioDTO> usuariosDTO = usuarios.stream()
					.map(user -> modelMapper.map(user, UsuarioDTO.class))
					.collect(Collectors.toList());
			
			return usuariosDTO;
		}
		return null;
	}
	
	/**
	 * Metodo que permite registrar los datos de un usuario
	 * por medio del metodo HTTP.POST
	 * 
	 * @return String
	 */
	@ApiOperation(value = "Metodo que registrar la infromacion de un nuevo usuario por medio del metodo HTTP.POST",
				  response = Integer.class, tags = "crearUsurio")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Exitoso, funcion!"),
		@ApiResponse(code = 401, message = "No autorizado, pilas!"),
		@ApiResponse(code = 403, message = "No tienes permiso!"),
		@ApiResponse(code = 404, message = "No se encontro lo que buscabas!"),
		@ApiResponse(code = 451, message = "Usuario ya existe! - 451 Unavailable For Legal Reasons.")
	})
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
				 produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuario) {
		if(null != usuario && (null != usuario.getNombre() && !"".equals(usuario.getNombre()))) {
			
			Usuarios uLogin = uJpaRepository.findByLogin(usuario.getLogin());
			
			if(null == uLogin) {
				Usuarios usuarioEntity = new Usuarios();
				
				usuarioEntity.setEstado(EstadosEnum.ACTIVO);
				usuarioEntity.setLogin(usuario.getLogin());
				usuarioEntity.setNombre(usuario.getNombre());
				
				usuarioEntity.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));
				
				usuarioEntity = uJpaRepository.save(usuarioEntity);
				
				if(null != usuarioEntity && usuarioEntity.getId() > 0) {
					ModelMapper modelMapper = new ModelMapper();
					
					UsuarioDTO usuarioMap = modelMapper.map(usuarioEntity, UsuarioDTO.class);
					
					usuarioMap.setIdUsuario(usuarioEntity.getId());
					
					usuario.setIdPublico(UUID.randomUUID().toString());
					
					return ResponseEntity.status(HttpStatus.OK).body(usuarioMap);
				}
				else
					return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
			}
			else
				return ResponseEntity.status(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS).body(null);
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
		
	}
	
	/**
	 * Metodo que permite actualizar los datos de un usuario
	 * por medio del metodo HTTP.PUT
	 * 
	 * @return String
	 */
	@PutMapping
	public @ResponseBody UsuarioDTO actualizarUsuario(UsuarioDTO usuario) {
		if(null != usuario && usuario.getIdUsuario() > 0) {
			
			Usuarios u = uJpaRepository.findById(usuario.getIdUsuario()).get();
			
			if(null != u && !u.getNombre().equals(usuario.getNombre())) {
				
				u.setNombre(usuario.getNombre());
	
				u = uJpaRepository.save(u);
				
				ModelMapper modelMapper = new ModelMapper();
				
				UsuarioDTO usuarioDTO = modelMapper.map(u, UsuarioDTO.class);
				
				return usuarioDTO;
			}
		}
		return null;
	}
	
	/**
	 * Este metodo permite eliminar la información
	 * de un usuario especifico
	 * 
	 * @return String
	 */
	@DeleteMapping
	public String eliminarUsuario(int idUsuario) {
		if(uJpaRepository.existsById(idUsuario)) {
			Optional<Usuarios> u = uJpaRepository.findById(idUsuario);
			if(u.isPresent()) {
				uJpaRepository.delete(u.get());
				uJpaRepository.flush();
				return "Se elimino el usuario exitosament!";
			}
		}
		return "No es posible eliminar el usuario, no existe un usuario con el identificador ingresado!";
	}
	
}
