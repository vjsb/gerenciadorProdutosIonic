package com.curso.gerenciadorProdutosSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.curso.gerenciadorProdutosSpring.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	//busca por email com SpringData
	//transaction para não necessitar ser envolvida em uma transação, isso deixa o método mais rapido
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);
	
}
