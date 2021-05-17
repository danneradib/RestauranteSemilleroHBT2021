package com.heinsohn.semillero.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.heinsohn.semillero.dto.UsuarioDTO;
import com.heinsohn.semillero.entity.Usuarios;
import com.heinsohn.semillero.enums.EstadosEnum;
import com.heinsohn.semillero.repository.UsuariosJpaRepository;

@Service
public class UsuariosServiceImpl implements UsuariosService {
	
	private UsuariosJpaRepository uJpaRepository;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UsuariosServiceImpl(UsuariosJpaRepository uJpaRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.uJpaRepository = uJpaRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	/**
	 * Este metodo permite persistir un usuario con la 
	 * informacion en la base de datos
	 * 
	 * @param usuario
	 * @return UsuarioDTO
	 */
	@Override
	public UsuarioDTO crearUsuario(UsuarioDTO usuario) {
		usuario.setIdUsuario(Integer.parseInt(UUID.randomUUID().toString()));
		
		Usuarios usuarioEntity = new Usuarios();
		
		usuarioEntity.setEstado(EstadosEnum.ACTIVO);
		usuarioEntity.setLogin(usuario.getLogin());
		usuarioEntity.setNombre(usuario.getNombre());
		
		usuarioEntity.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));
		
		usuarioEntity = uJpaRepository.save(usuarioEntity);
		
		if(null != usuarioEntity && usuarioEntity.getId() > 0) {
			ModelMapper modelMapper = new ModelMapper();
			
			UsuarioDTO usuarioMap = modelMapper.map(usuarioEntity, UsuarioDTO.class);
			
			return usuarioMap;
		}
		return null;
	}

	/**
	 * Este metodo permite consultar la informacion de los usuarios
	 * 
	 * @return List<UsuarioDTO>
	 */
	@Override
	public List<UsuarioDTO> consultarUsuarios() {
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
	 * Este metodo permite consultar la infromacion de un usuario filtrando por el id
	 * 
	 * @param id
	 * @return boolean, true si se elimina, false en caso contrario
	 */
	@Override
	public boolean eliminarUsuarioXId(int id) {
		if(uJpaRepository.existsById(id)) {
			Optional<Usuarios> u = uJpaRepository.findById(id);
			if(u.isPresent()) {
				uJpaRepository.delete(u.get());
				uJpaRepository.flush();
				return true;
			}
		}
		return false;
	}

	/**
	 * Este metodo permite actualizar la informacion del usuario
	 * 
	 * @param usuario
	 * @return UsuarioDTO
	 */
	@Override
	public UsuarioDTO actualizarUsuario(UsuarioDTO usuario) {
		if(null != usuario && usuario.getIdUsuario() > 0) {
			
			Usuarios u = uJpaRepository.findById(usuario.getIdUsuario()).get();
			
			if(null != u) {
				ModelMapper modelMapper = new ModelMapper();

				u = uJpaRepository.save(u);
				
				UsuarioDTO usuarioDTO = modelMapper.map(u, UsuarioDTO.class);
				
				return usuarioDTO;
			}
		}
		return null;
	}

	/**
	 * Este metodo permite consultar la informacion del usaurio por el login
	 * 
	 * @param login
	 * @return UsuarioDTO
	 */
	@Override
	public UsuarioDTO consultarUsuarioXLogin(String login) {
		Usuarios u = uJpaRepository.findByLogin(login);
		
		if(null == u)
			throw new UsernameNotFoundException(login);
		
		if(null != u && u.getId() > 0) {
			ModelMapper modelMapper = new ModelMapper();
			
			UsuarioDTO usuario = modelMapper.map(u, UsuarioDTO.class);
			
			return usuario;
		}
		return null; 
	}

	/**
	 * Este metodo permite consultar la infromacion del usuario
	 * filtrando por el login
	 * 
	 * @param login
	 * @return UsuarioDTO
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuarios u = uJpaRepository.findByLogin(username);
		
		if(null == u)
			throw new UsernameNotFoundException(username);
		
		if(null != u && u.getId() > 0) {
			return new User(u.getLogin(), u.getPassword(), true, true, true, true, new ArrayList<>());
		}
		return null;
	}

	@Override
	public UsuarioDTO getUsetDateilsByUserLogin(String login) {
		Usuarios u = uJpaRepository.findByLogin(login);
		
		if(null == u)
			throw new UsernameNotFoundException(login);
		
		UsuarioDTO usuario = new ModelMapper().map(u, UsuarioDTO.class);
		
		usuario.setIdUsuario(u.getId());
		
		return usuario;
	}

}
