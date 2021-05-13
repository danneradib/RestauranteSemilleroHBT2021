package com.heinsohn.semillero.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.heinsohn.semillero.entity.Usuarios;

public interface UsuariosPagSortRepository extends PagingAndSortingRepository<Usuarios, Integer> {

}
