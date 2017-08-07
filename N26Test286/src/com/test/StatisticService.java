package com.test;

import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import org.json.JSONObject;
import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;

@Path("/get")
public class StatisticService {

	static Prevayler<TransacList> prevayler;

	@GET
	@Path("/statistics")
	@Produces("application/json")
	public static void getStatisticInJSON(JSONObject json) throws InterruptedException {
		
		
        Client restClient = Client.create();
        WebResource webResource = restClient.resource(json.toString());
        ClientResponse resp = webResource.accept("application/json").get(ClientResponse.class);
        
        if(resp.getStatus() != 200){
            System.err.println("Unable to connect to the server");
        }
        
        String output = resp.getEntity(String.class);
        System.out.println("response: "+output);
		
	}

	public static void main(String a[]) throws InterruptedException{
		generateStatistic();
	}
	
	public static void generateStatistic() throws InterruptedException {
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

		TransacList transaclist;
		Long timeCalculated = System.currentTimeMillis();
		DoubleSummaryStatistics stats;  


		while(true) {
			Thread.sleep(System.currentTimeMillis() - timeCalculated);
			transaclist = ((TransacList) prevayler.prevalentSystem());
			Long highestTimeCutForStatistic = timeCalculated + 60000;
			Long lowestTimeCutForStatistic = timeCalculated;

			if(!transaclist.getTransacList().isEmpty()) {
				stats = transaclist.getTransacList()
						.stream()
						.filter(x -> lowestTimeCutForStatistic >= x.getTime() && highestTimeCutForStatistic < x.getTime())
						.collect(Collectors.summarizingDouble(Transac::getAmount));

				System.out.println(stats);

				transaclist.getTransacList()
				.stream()
				.filter(x -> lowestTimeCutForStatistic >= x.getTime() && highestTimeCutForStatistic < x.getTime())
				.forEach(x -> new DeleteTransaction(x));

				JSONObject json = new JSONObject();
				json.put("sum", stats.getSum());
				json.put("avg", stats.getAverage());
				json.put("max", stats.getMax());
				json.put("min", stats.getMin());
				json.put("count", stats.getCount());
				getStatisticInJSON(json);

			}

			timeCalculated = highestTimeCutForStatistic;
		}
	}
}
