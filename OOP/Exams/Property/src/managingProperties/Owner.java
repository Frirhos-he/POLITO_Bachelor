package managingProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Owner {

	private String name;
	private Map<Integer, Apartment> apartments = new HashMap<>();
	private Map<Integer, Request> requests = new TreeMap<>();
	private Integer costs = 0;
	
	public Owner(String name) {
		
		this.name = name;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Map<Integer, Apartment> getApartments() {
		return apartments;
	}


	public void setApartments(Map<Integer, Apartment> apartments) {
		this.apartments = apartments;
	}


	public Map<Integer, Request> getRequests() {
		return requests;
	}


	public void setRequests(Map<Integer, Request> requests) {
		this.requests = requests;
	}


	public Integer getCosts() {
		return costs;
	}


	public void setCosts(Integer costs) {
		this.costs = costs;
	}
	
	
}
