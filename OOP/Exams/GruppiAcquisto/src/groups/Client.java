package groups;

import java.util.HashMap;
import java.util.Map;

public class Client {
	
	private String name;
	private Map<String, Group> groups = new HashMap<>();
	
	
	
	public Client(String name) {

		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, Group> getGroups() {
		return groups;
	}
	public void setGroups(Map<String, Group> groups) {
		this.groups = groups;
	} 
}
