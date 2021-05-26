package com.heinsohn.semillero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.heinsohn.semillero.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

//	@Query(value = "", nativeQuery = true)
//	List<Menu> getMenuUsuario();
	
}
