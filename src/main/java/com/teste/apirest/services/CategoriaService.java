package com.teste.apirest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.apirest.entities.Categoria;
import com.teste.apirest.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public List<Categoria> findAll(){
		return repository.findAll();
	}
	
	public Categoria findById(Long id){
		Optional<Categoria> opCategoria = repository.findById(id);
		return opCategoria.get();
	}
	
}
