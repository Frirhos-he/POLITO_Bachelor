package ticketing;


import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import ticketing.Ticket.State;

public class IssueManager {
	
	private Map<String, User> users = new HashMap<>();
	private Map<String, Component> components = new HashMap<>();
	private Integer ticketID = 0;
	private Map<Integer, Ticket> tickets = new HashMap<>();
	

    /**
     * Eumeration of valid user classes
     */
    public static enum UserClass {
        /** user able to report an issue and create a corresponding ticket **/
        Reporter, 
        /** user that can be assigned to handle a ticket **/
        Maintainer }
    
    /**
     * Creates a new user
     * 
     * @param username name of the user
     * @param classes user classes
     * @throws TicketException if the username has already been created or if no user class has been specified
     */
    public void createUser(String username, UserClass... classes) throws TicketException {
    	
    	if(classes.length == 0) throw new TicketException();
    	if(users.containsKey(username)) throw new TicketException();
    	
    	
    	Set<UserClass> tmp = new TreeSet<>();
    	for(UserClass ele : classes) {
    		tmp.add(ele);
    	}
 
    	User element = new User(username,tmp);
    	users.put(username, element);
        
    }

    /**
     * Creates a new user
     * 
     * @param username name of the user
     * @param classes user classes
     * @throws TicketException if the username has already been created or if no user class has been specified
     */
    public void createUser(String username, Set<UserClass> classes) throws TicketException {
        
    	if(classes.size() == 0) throw new TicketException();
    	if(users.containsKey(username)) throw new TicketException();
    	
    	User element = new User(username,classes);
    	users.put(username, element);
    	
    }
   
    /**
     * Retrieves the user classes for a given user
     * 
     * @param username name of the user
     * @return the set of user classes the user belongs to
     */
    public Set<UserClass> getUserClasses(String username){
        return users.get(username).getClasses();
    }
    
    /**
     * Creates a new component
     * 
     * @param name unique name of the new component
     * @throws TicketException if a component with the same name already exists
     */
    public void defineComponent(String name) throws TicketException {
    	
    	if(components.containsKey(name)) throw new TicketException();
    	
    	
        Component element = new Component(name);
        components.put("/"+name, element);
        
    }
    
    /**
     * Creates a new sub-component as a child of an existing parent component
     * 
     * @param name unique name of the new component
     * @param parentPath path of the parent component
     * @throws TicketException if the the parent component does not exist or 
     *                          if a sub-component of the same parent exists with the same name
     */
    public void defineSubComponent(String name, String parentPath) throws TicketException {
        
    	
    	
    	if(!components.containsKey(parentPath)) throw new TicketException();
    	if(parentPath.contains(name)) throw new TicketException();
    	
    	
    	Component parent = components.get(parentPath);
    	
    	Component element = new Component(name);
    	components.put(parentPath+"/"+name, element);
    	
    	parent.addChilds(element);
    	
    	
    }
    
    /**
     * Retrieves the sub-components of an existing component
     * 
     * @param path the path of the parent
     * @return set of children sub-components
     */
    public Set<String> getSubComponents(String path){
    	
    	Component element = components.get(path);
    	
        return element.getChilds().keySet();
    }

    /**
     * Retrieves the parent component
     * 
     * @param path the path of the parent
     * @return name of the parent
     */
    public String getParentComponent(String path){
    	
    	Integer pos = path.lastIndexOf("/");
    	if(path.substring(0,pos).length() == 0) return null;
    	
    	
    	
        return path.substring(0, pos);
    }

    /**
     * Opens a new ticket to report an issue/malfunction
     * 
     * @param username name of the reporting user
     * @param componentPath path of the component or sub-component
     * @param description description of the malfunction
     * @param severity severity level
     * 
     * @return unique id of the new ticket
     * 
     * @throws TicketException if the user name is not valid, the path does not correspond to a defined component, 
     *                          or the user does not belong to the Reporter {@link IssueManager.UserClass}.
     */
    public int openTicket(String username, String componentPath, String description, Ticket.Severity severity) throws TicketException {
    	
    	ticketID++;
    	
    	
    	if(!users.containsKey(username)) throw new TicketException();
    	if(!users.get(username).getClasses().contains(UserClass.Reporter)) throw new TicketException();
    	if(!components.containsKey(componentPath)) throw new TicketException();
    	
    	
    	Ticket element = new Ticket(this.ticketID, users.get(username), componentPath, description, severity);
    
    	tickets.put(ticketID, element);
    	
        return ticketID;
    }
    
    /**
     * Returns a ticket object given its id
     * 
     * @param ticketId id of the tickets
     * @return the corresponding ticket object
     */
    public Ticket getTicket(int ticketId){
    	
    	if(!tickets.containsKey(ticketId)) return null;
    	else 
        return tickets.get(ticketId);
    }
    
    /**
     * Returns all the existing tickets sorted by severity
     * 
     * @return list of ticket objects
     */
    public List<Ticket> getAllTickets(){
        return tickets.values().stream().sorted(Comparator.comparing(Ticket::getState)).collect(Collectors.toList());
    }
    
    /**
     * Assign a maintainer to an open ticket
     * 
     * @param ticketId  id of the ticket
     * @param username  name of the maintainer
     * @throws TicketException if the ticket is in state <i>Closed</i>, the ticket id or the username
     *                          are not valid, or the user does not belong to the <i>Maintainer</i> user class
     */
    public void assingTicket(int ticketId, String username) throws TicketException {
        
    	if(!tickets.containsKey(ticketId)) throw new TicketException();
    	if(!users.containsKey(username))  throw new TicketException();
    	if(!users.get(username).getClasses().contains(UserClass.Maintainer))  throw new TicketException();
    	if(!tickets.get(ticketId).getState().equals(State.Open)) throw new TicketException();
    	
    	
    	tickets.get(ticketId).setState(State.Assigned);
    	tickets.get(ticketId).setUser(users.get(username));
    	users.get(username).getTickets().put(ticketID, tickets.get(ticketId));
    	
    	
    }

    /**
     * Closes a ticket
     * 
     * @param ticketId id of the ticket
     * @param description description of how the issue was handled and solved
     * @throws TicketException if the ticket is not in state <i>Assigned</i>
     */
    public void closeTicket(int ticketId, String description) throws TicketException {
        
    	
    	if(!tickets.get(ticketId).getState().equals(State.Assigned)) throw new TicketException();
    	
    	
    	tickets.get(ticketId).setSolution(description);
    	
    	tickets.get(ticketId).setState(State.Closed);
    }

    /**
     * returns a sorted map (keys sorted in natural order) with the number of  
     * tickets per Severity, considering only the tickets with the specific state.
     *  
     * @param state state of the tickets to be counted, all tickets are counted if <i>null</i>
     * @return a map with the severity and the corresponding count 
     */
    public SortedMap<Ticket.Severity,Long> countBySeverityOfState(Ticket.State state){
    	
    	if(state == null) {
    		
    		return tickets.values().stream().collect(Collectors.groupingBy(
    				Ticket::getSeverity,
    				()->new TreeMap<Ticket.Severity,Long>(Comparator.naturalOrder()),
    				Collectors.counting()
    				));
    	}
    	
    	else {
    		return tickets.values().stream().filter(x->x.getState().equals(state)).collect(Collectors.groupingBy(
    				Ticket::getSeverity,
    				()-> new TreeMap<Ticket.Severity,Long>(Comparator.naturalOrder()),
    				Collectors.counting()
    				));
    		
    	}
        
    }

    /**
     * Find the top maintainers in terms of closed tickets.
     * 
     * The elements are strings formatted as <code>"username:###"</code> where <code>username</code> 
     * is the user name and <code>###</code> is the number of closed tickets. 
     * The list is sorter by descending number of closed tickets and then by username.

     * @return A list of strings with the top maintainers.
     */
    public List<String> topMaintainers(){
    	
    	return users.values().stream().sorted(Comparator.comparing(User::NumClose).reversed().thenComparing(x->x.getUsername()))
    	.map(x->x.getUsername()+":"+x.NumClose()).limit(3).collect(Collectors.toList());

    }

}
