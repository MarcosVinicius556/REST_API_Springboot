package com.teste.apirest.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.teste.apirest.entities.enums.PedidoStatus;

@Entity
public class Pedido implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	//Após java 8 foi adicionado
	//Melhor que o Date
	//Formatar o atributo do JSON
	//Formatando a data
	@JsonFormat(shape=JsonFormat.Shape.STRING, 
				pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",
				timezone = "GMT")
	private Instant moment;
	
	//Trata internamente como Integer
	private Integer pedidoStatus;
	
	//Mapeamento de relacionamento entre tabelas
	//Por padrão em uma associação de muitos para 1
	//O jpa traz o objeto associado junto
	@ManyToOne
	@JoinColumn(name="client_id")
	private Usuario user;

	//Mapeado pela classe auxiliar que possui o id composto
	@OneToMany(mappedBy="id.order")
	private Set<OrderItem> itens = new HashSet<>();
	
	//Mapeamento 1 para 1
	//No caso do 1 para 1 estamos mapeamento para ter o mesmo id que a classe payment
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;
	
	public Pedido() {
		
	}
	
	public Pedido(Long id, Instant moment, PedidoStatus pedidoStatus, Usuario user) {
		super();
		this.id = id;
		this.moment = moment;
		setPedidoStatus(pedidoStatus);
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public PedidoStatus getPedidoStatus() {
		return PedidoStatus.valueOf(pedidoStatus);
	}

	public void setPedidoStatus(PedidoStatus pedidoStatus) {
		if(pedidoStatus != null) {
			this.pedidoStatus = pedidoStatus.getCode();
		}
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Set<OrderItem> getItems(){
		return itens;
	}
	
	public Double getTotal() {
		double soma = 0;
		for(OrderItem item : itens) {
			soma += item.getSubTotal();
		}
		return soma;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
