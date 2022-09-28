package com.teste.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.apirest.entities.Usuario;

//Por padrão a interface JPARepository já tem métodos como Insert, remove, etc....
//Similar ao padrão de projeto DAO
public interface UserRepository extends JpaRepository<Usuario, Long>{

	
	
}
