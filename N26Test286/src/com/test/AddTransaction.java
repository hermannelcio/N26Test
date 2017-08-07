package com.test;

import java.io.Serializable;
import java.util.Date;

import org.prevayler.Transaction;

public class AddTransaction implements Serializable, Transaction<Object>{
	
	private static final long serialVersionUID = 3L;
	
		private Transac transac;  
	  
	    public AddTransaction(Transac transac) {  
	        this.transac = transac;  
	    }  
	  
	    public void executeOn(Object businessSystem, Date date) {  
	        ((TransacList) businessSystem).add(new Transac(transac));  
	    }  

}
