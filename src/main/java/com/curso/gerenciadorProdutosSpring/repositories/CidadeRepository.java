package com.curso.gerenciadorProdutosSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.gerenciadorProdutosSpring.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

}
