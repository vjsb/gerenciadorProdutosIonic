package com.curso.gerenciadorProdutosSpring.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.curso.gerenciadorProdutosSpring.domain.Categoria;

// DTO são criados para passar somente oque desejamos de cada objeto, nesse caso id e nome
public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 80, message = "O tamanho deve ser de 5 a 80 caracteres")
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
