package com.test;

import java.io.Serializable;

public class Transac implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Double amount;
	private Long time;
	
	public Transac (Double amount, Long time) {
		this.amount = amount;
		this.time = time;
	}
	
	public Transac (Transac transac) {
		this.amount = transac.getAmount();
		this.time = transac.getTime();
	}

	public Double getAmount() {
		return amount;
	}

 
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getTime() {
		return time;
	}

	
	public void setTime(Long time) {
		this.time = time;
	}
	
	

}
