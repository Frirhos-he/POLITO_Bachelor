package transactions;

import java.util.Map;
import java.util.TreeMap;

public class Place {

	
	private String name;
	private Region regions;
	Map<String,Request> requests = new TreeMap<>();
	Map<String,Offer> offers = new TreeMap<>();
	Map<String, Transaction> rTransaction = new TreeMap<>();
	Map<String, Transaction> oTransaction = new TreeMap<>();
	

	
	
	public Place(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Region getRegions() {
		return regions;
	}

	public void setRegions(Region regions) {
		this.regions = regions;
	}


	public Map<String, Request> getRequests() {
		return requests;
	}

	public void setRequests(Map<String, Request> requests) {
		this.requests = requests;
	}

	public Map<String, Offer> getOffers() {
		return offers;
	}

	public void setOffers(Map<String, Offer> offers) {
		this.offers = offers;
	}

	public Map<String, Transaction> getrTransaction() {
		return rTransaction;
	}

	public void setrTransaction(Map<String, Transaction> rTransaction) {
		this.rTransaction = rTransaction;
	}

	public Map<String, Transaction> getoTransaction() {
		return oTransaction;
	}

	public void setoTransaction(Map<String, Transaction> oTransaction) {
		this.oTransaction = oTransaction;
	}



}
