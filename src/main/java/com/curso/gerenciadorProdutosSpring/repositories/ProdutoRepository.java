package com.curso.gerenciadorProdutosSpring.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.curso.gerenciadorProdutosSpring.domain.Categoria;
import com.curso.gerenciadorProdutosSpring.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

	// Query feita em JPQL, Distinct serve para não trazer repetido
	// @Param serve para definir onde cada parametro vai, no caso queremos buscar o nome e categorias então colocamos na frente
	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	Page<Produto> search(@Param("nome")String nome,@Param("categorias")List<Categoria> categorias, Pageable pageRequest);
	
	//método abaixo faz a mesma coisa que o acima só que usando pesquisas chaves do spring data
	//Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);

}
