package com.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class TransacList implements Serializable{
	
	private static final long serialVersionUID = 2L;
	
	private List<Transac> transacList = new ArrayList<Transac>();
	
	 public void add(Transac transac) {  
		 transacList.add(transac);  
     }
	 
	 public void delete(Transac transac) {  
		 transacList.remove(transac);  
     }
	 
     public Transac get(int i) {           
         return transacList.get(i);  
     }
     
     public int size() {  
        return transacList.size();  
     }

	public List<Transac> getTransacList() {
		return transacList;
	}  
	
}
