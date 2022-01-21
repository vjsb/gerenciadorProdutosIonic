package com.curso.gerenciadorProdutosSpring.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable {
		private static final long serialVersionUID = 1L;

	 //associação feita para ligar o pedido ao produto, conforme o diagrama
	 //EmbeddedId por conta de ser um id embutido em um tipo auxiliar
	 @JsonIgnore
	 @EmbeddedId
	 private ItemPedidoPK id = new ItemPedidoPK();
	 
	 private Double desconto;
	 
	 private Integer quantidade;
	 
	 private Double precoDouble;
	 
	 public ItemPedido() {
		 
	 }

	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double precoDouble) {
		super();
		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.precoDouble = precoDouble;
	}
	
	//fazer o subtotal dos pedidos
	public double getSubTotal() {
		return (precoDouble - desconto) * quantidade;
	}

	@JsonIgnore
	public Pedido getPedido() {
		return id.getPedido();
	}
	
	public Produto getProduto() {
		return id.getProduto();
	}
	
	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPrecoDouble() {
		return precoDouble;
	}

	public void setPrecoDouble(Double precoDouble) {
		this.precoDouble = precoDouble;
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
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	 
	 
	 
	 
}
