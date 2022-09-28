package com.teste.apirest.entities.enums;

public enum PedidoStatus {
	
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	private Integer code;
	
	private PedidoStatus(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}
	
	//Percorre todos os valores ppossíveis, se encontrar retorna
	public static PedidoStatus valueOf(Integer code) {
		for(PedidoStatus value : PedidoStatus.values()) {
			if(value.getCode() == code) {
				return value;
			} 
		}
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
}
