package com.teste.apirest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.apirest.entities.OrderItem;
import com.teste.apirest.entities.pk.OrderItemPk;
import com.teste.apirest.repositories.OrderItemRepository;

@Service
public class OrderItemService {
	
	@Autowired
	private OrderItemRepository repository;
	
	public List<OrderItem> findAll(){
		return repository.findAll();
	}
	
	public OrderItem findById(OrderItemPk id){
		Optional<OrderItem> opOrderItem = repository.findById(id);
		return opOrderItem.get();
	}
}
