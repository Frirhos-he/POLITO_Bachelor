package ticketing;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import ticketing.IssueManager.UserClass;
import ticketing.Ticket.State;

public class User {
	
	private String username;
	private Set<UserClass> classes;
	private Map<Integer, Ticket> tickets = new HashMap<>();


	
	public Long NumClose() {
		
		return tickets.values().stream().filter(x->x.getState().equals(State.Closed)).count();
		 
	}
	public User(String username, Set<UserClass> tmp) {

		this.username = username;
		this.setClasses(tmp);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<UserClass> getClasses() {
		return classes;
	}

	public void setClasses(Set<UserClass> classes) {
		this.classes = classes;
	}

	public Map<Integer, Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Map<Integer, Ticket> tickets) {
		this.tickets = tickets;
	}


}
