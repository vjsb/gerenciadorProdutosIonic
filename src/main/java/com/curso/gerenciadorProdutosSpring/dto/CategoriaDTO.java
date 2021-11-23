package com.curso.gerenciadorProdutosSpring.dto;

import java.io.Serializable;

import com.curso.gerenciadorProdutosSpring.domain.Categoria;

// DTO são criados para passar somente oque desejamos de cada objeto, nesse caso id e nome
public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String nome;
	
	public CategoriaDTO() {
		
	}
	
	//construtor ira receber oque desejo da classe domain.Categoria
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	
	
}
