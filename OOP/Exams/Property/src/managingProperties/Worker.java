package managingProperties;

import java.util.Map;
import java.util.TreeMap;

public class Worker {

	private String id;
	private Professional profession;
	private Map<String, Integer> costs = new TreeMap<>();
	
	
	public Worker(String id, Professional profession) {
		super();
		this.id = id;
		this.profession = profession;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Professional getProfession() {
		return profession;
	}

	public void setProfession(Professional profession) {
		this.profession = profession;
	}

	public Map<String, Integer> getCosts() {
		return costs;
	}

	public void setCosts(Map<String, Integer> costs) {
		this.costs = costs;
	}
	
}
