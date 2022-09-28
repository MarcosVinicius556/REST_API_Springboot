package com.teste.apirest.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.apirest.entities.Categoria;
import com.teste.apirest.services.CategoriaService;

@RestController
@RequestMapping(value="/categories")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;	
	
	@GetMapping
	public ResponseEntity<List<Categoria>> finAll(){
		List<Categoria> listCategoria = service.findAll();
		return ResponseEntity.ok().body(listCategoria);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Long id){
		Categoria categoria = service.findById(id);
		return ResponseEntity.ok().body(categoria);
				
	}
}
