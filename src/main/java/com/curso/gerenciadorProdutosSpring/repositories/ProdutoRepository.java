package com.curso.gerenciadorProdutosSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.gerenciadorProdutosSpring.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
