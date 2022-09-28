package com.teste.apirest.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.apirest.entities.Pedido;
import com.teste.apirest.services.PedidoService;

@RestController
@RequestMapping(value="/order")
public class PedidoResource {

	//Auto injeção de dependencia 
	@Autowired
	private PedidoService service;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> findAll(){
		List<Pedido> listPedido = service.findAll();
		return ResponseEntity.ok().body(listPedido);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Long id){
		Pedido pedido = service.findById(id);
		return ResponseEntity.ok().body(pedido);
	}
	
}
