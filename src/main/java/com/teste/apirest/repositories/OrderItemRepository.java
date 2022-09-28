package com.teste.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.apirest.entities.OrderItem;
import com.teste.apirest.entities.pk.OrderItemPk;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPk>{

}
