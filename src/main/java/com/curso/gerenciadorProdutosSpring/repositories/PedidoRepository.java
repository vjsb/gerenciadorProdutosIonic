package com.curso.gerenciadorProdutosSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.gerenciadorProdutosSpring.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
