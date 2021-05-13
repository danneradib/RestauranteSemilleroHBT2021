package com.heinsohn.semillero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.heinsohn.semillero.entity.Usuarios;

public interface UsuariosJpaRepository extends JpaRepository<Usuarios, Integer> {

//	@Query("SELECT u FROM usuarios u WHERE u.login = ?1")
//	Usuarios consultarUsuarioLogin(String login);
	
	@Query(value = "SELECT * FROM usuarios u WHERE u.estado = ?1", 
		   nativeQuery = true)
	List<Usuarios> consultarUsuariosActivos(String estado);
	
}
