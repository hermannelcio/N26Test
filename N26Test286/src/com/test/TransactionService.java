package com.test;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;


@Path("/post") 
public class TransactionService {

	private static final String SUCCESS_RESULT = "201";
	private static final String LATE_RESULT = "204";

	static Prevayler<TransacList> prevayler;  
	static Transac transac;

	@POST
	@Path("/transaction")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JSONObject createTransaction(@FormParam("amount") Double amount, @FormParam("time") Long time){

		transac = new Transac(amount, time);

		System.out.println(amount);
		System.out.println(time);

		JSONObject json = new JSONObject();
		
		System.out.println(System.currentTimeMillis());

		if ((System.currentTimeMillis() - time) > 60000)
			json.put("", LATE_RESULT);
		else
			json.put("", SUCCESS_RESULT);
		
		System.out.println(json.toString());

		if (prevayler == null) {
			PrevaylerFactory<TransacList> factory = new PrevaylerFactory<TransacList>();  
			factory.configurePrevalenceDirectory("BASE");  
			factory.configurePrevalentSystem(new TransacList());  
			try {
				prevayler = factory.create();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		prevayler.execute(new AddTransaction(transac));  

		return json;
	}


}
