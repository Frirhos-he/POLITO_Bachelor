package ticketing;

/**
 * Class representing the ticket linked to an issue or malfunction.
 * 
 * The ticket is characterized by a severity and a state.
 */
public class Ticket {
    
	
	
	private Integer ticketID;
	private User user;
	private String component;
	private String description;
	private Severity severity;
	private State state ;
	private User assigned;
	private String solution;
    /**
     * Enumeration of possible severity levels for the tickets.
//     * 
     * Note: the natural order corresponds to the order of declaration
     */
    public enum Severity { Blocking, Critical, Major, Minor, Cosmetic };
    
    /**
     * Enumeration of the possible valid states for a ticket
     */
    public static enum State { Open, Assigned, Closed }
    
 
	public Ticket(Integer ticketID2, User user, String componentPath, String description, Severity severity) {
		this.ticketID = ticketID2;
		this.user = user;
		this.component = componentPath;
		this.description = description;
		this.severity = severity;
		this.state =  State.Open;
	}

	public int getId(){
        return ticketID;
    }

    public String getDescription(){
        return description;
    }
    
    public Severity getSeverity() {
        return this.severity;
    }

    public String getAuthor(){
        return this.user.getUsername();
    }
    
    public String getComponent(){
        return component;
    }
    
    public State getState(){
        return this.state;
    }
    
    public String getSolutionDescription() throws TicketException {
    	if(!this.state.equals(State.Closed)) throw new TicketException();
        return solution;
    }

	public Integer getTicketID() {
		return ticketID;
	}

	public void setTicketID(Integer ticketID) {
		this.ticketID = ticketID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public void setState(State state) {
		this.state = state;
	}

	public User getAssigned() {
		return assigned;
	}

	public void setAssigned(User assigned) {
		this.assigned = assigned;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}
}
