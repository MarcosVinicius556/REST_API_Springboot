package com.teste.apirest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.apirest.entities.Product;
import com.teste.apirest.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	public Product findById(Long id) {
		Optional<Product> opProduct = repository.findById(id); 
		return  opProduct.get();
	}

	public List<Product> findAll(){
		return repository.findAll();
	}
	
	
}
