package com.heinsohn.semillero.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.heinsohn.semillero.entity.Usuarios;

@Repository
public interface UsuariariosRepository extends CrudRepository<Usuarios, Integer> {
	
	
	
}
