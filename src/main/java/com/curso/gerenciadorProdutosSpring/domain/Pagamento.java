package com.curso.gerenciadorProdutosSpring.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.curso.gerenciadorProdutosSpring.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonBackReference;


/*
 * inheritance serve para mapear uma herança
 * serve para fazer a junção ou não das tabelas herdadas pela superclasse
 * nas classes herdadas só colocamos o entity
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L; 

	@Id
	private Integer id;
	
	private Integer estado;
	
	/*
	 * o id de pagamento e pedido tem que ser o mesmo no banco
	 * oneToOne faz a relação ser um para um sendo assim tendo que ter o mesmo id
	 * a coluna criada sera o pedido_id, para ficar facil identificar no banco quem é o campo que ta mapenaod com pedido
	 * MapsId serve para garantir que o id do pagamento seja o mesmo que o do pedido
	 * 
	 */
	@JsonBackReference
	@OneToOne
	@JoinColumn(name="pedido_id")
	@MapsId
	private Pedido pedido;
	
	public Pagamento() {
		
	}
	
	public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
		super();
		this.id = id;
		this.estado = estado.getCod();
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado);
	}

	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
