package managingProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Professional {

	private String name;
	
	private Map<String, Worker> workers = new HashMap<>();
	private Map<Integer, Request> requests = new TreeMap<>();
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Worker> getWorkers() {
		return workers;
	}

	public void setWorkers(Map<String, Worker> workers) {
		this.workers = workers;
	}

	public Professional(String name) {
		super();
		this.name = name;
	}

	public Map<Integer, Request> getRequests() {
		return requests;
	}

	public void setRequests(Map<Integer, Request> requests) {
		this.requests = requests;
	}

	
	
}