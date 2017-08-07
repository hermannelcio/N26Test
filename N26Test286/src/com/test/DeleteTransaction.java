package com.test;

import java.io.Serializable;
import java.util.Date;

import org.prevayler.Transaction;

public class DeleteTransaction implements Serializable, Transaction<Object>{

	private static final long serialVersionUID = 5L;

	private Transac transac;  

	public DeleteTransaction(Object x) {  
		this.transac = (Transac) x;  
	}

	public void executeOn(Object businessSystem, Date date) {  
		((TransacList) businessSystem).delete(transac);  
	}  

}
