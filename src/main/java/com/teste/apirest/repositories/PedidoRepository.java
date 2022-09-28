package com.teste.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.apirest.entities.Pedido;

// Não necessário em caso de repositório
// pois já extende a classe
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
