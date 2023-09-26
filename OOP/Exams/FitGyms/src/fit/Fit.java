package fit;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;


public class Fit {
    
    public static int MONDAY    = 1;
    public static int TUESDAY   = 2;
    public static int WEDNESDAY = 3;
    public static int THURSDAY  = 4;
    public static int FRIDAY    = 5;
    public static int SATURDAY  = 6;
    public static int SUNDAY    = 7;
    
    private Map<String, Gym> gyms = new HashMap<>();
    private Integer globalC = 1;
    private Map<Integer, Customer> customers = new HashMap<>();
    
	public Fit() {
	
	}
	// R1 
	
	public void addGymn (String name) throws FitException {

		if(gyms.containsKey(name)) throw new FitException();
		else {
			Gym element = new Gym(name);
			gyms.put(name, element);
		}
	}
	
	public int getNumGymns() {
		return gyms.size();
	}
	
	//R2

	public void addLessons (String gymnname, 
	                        String activity, 
	                        int maxattendees, 
	                        String slots, 
	                        String ...allowedinstructors) throws FitException{
		
//		•the name of the gym is wrong 
//		•the day of the week or the slot are not in the predetermined ranges
//		•not all the slots requested are available in the gym indicated (i.e. some of them may already be occupied).

	    if(!gyms.containsKey(gymnname)) throw new FitException();
	    List<Slot> slotsList = new ArrayList<>(); 
	    for(String tmp: slots.split(",")) {
	    	
	    	String[] elem = tmp.split("\\.");
	    	if(Integer.parseInt(elem[1])<8 || Integer.parseInt(elem[1])>20) throw new FitException();
	    	if(Integer.parseInt(elem[0])<1 || Integer.parseInt(elem[0])>7) throw new FitException();
	    	
	    	slotsList.add(new Slot(Integer.parseInt(elem[0]),Integer.parseInt(elem[1])));
	    	
	    }
	    
	    for(Slot tmp: slotsList) {
	    	
	    	
	    	if(gyms.get(gymnname).getSlots().containsKey(tmp)) throw new FitException();
	    	else {
	    	Lesson element = new Lesson(gyms.get(gymnname),activity, maxattendees, tmp, Arrays.asList(allowedinstructors));
	    	gyms.get(gymnname).getSlots().put(tmp, element);
	    	}
	    	
	    }

	   
	}
	
	//R3
	public int addCustomer(String name) {
		
		Customer element = new Customer (this.globalC, name);
		customers.put(this.globalC,element);
		
		return globalC++;
	}
	
	public String getCustomer (int customerid) throws FitException{
		
		if(!customers.containsKey(customerid)) throw new FitException();
		
		
	    return customers.get(customerid).getName();
	}
	
	//R4
	
	public void placeReservation(int customerid, String gymnname, int day, int slot) throws FitException{

			Slot eleS = new Slot(day,slot);
			if(!gyms.containsKey(gymnname)) throw new FitException();
			else if( gyms.get(gymnname).getSlots().containsKey(eleS) && gyms.get(gymnname).getSlots().get(eleS).isMax() == true) throw new FitException();
			else if(slot<8 || slot>20) throw new FitException();
			else if(day<1 || day>7) throw new FitException();
			else if(!gyms.get(gymnname).getSlots().containsKey(eleS)) throw new FitException();
				else {
				if(!customers.containsKey(customerid)) throw new FitException();
				if(customers.get(customerid).getReservations().containsKey(eleS)) throw new FitException();
				gyms.get(gymnname).getSlots().get(eleS).setCurrentattendee(
						gyms.get(gymnname).getSlots().get(eleS).getCurrentattendee()+1
						);
				

				
				Reservation element  = new Reservation (customers.get(customerid), gyms.get(gymnname),
				eleS);
				customers.get(customerid).getReservations().put(eleS, element);
			}
			
	}
	
	public int getNumLessons(int customerid) {
		return customers.get(customerid).getReservations().size();
	}
	
	
	//R5
	
	public void addLessonGiven (String gymnname, int day, int slot, String instructor) throws FitException{
		
		Slot eleS = new Slot(day,slot);
		if(!gyms.containsKey(gymnname)) throw new FitException();
		if(slot<8 || slot>20) throw new FitException();
    	if(day<1 || day>7) throw new FitException();
    	if(!gyms.get(gymnname).getSlots().containsKey(eleS)) throw new FitException();
    	if(!gyms.get(gymnname).getSlots().get(eleS).getAllowedinstructors().contains(instructor)) throw new FitException();
    	
    	
    	gyms.get(gymnname).getSlots().get(eleS).setInstructor(instructor);
    	
    	

	}
	
	public int getNumLessonsGiven (String gymnname, String instructor) throws FitException {
		
		if(!gyms.containsKey(gymnname)) throw new FitException();
		int num = (int) gyms.get(gymnname).getSlots().values().stream().filter(x->x.getInstructor().equals(instructor))
		    	.count();
	    return num;
	}
	//R6
	
	public String mostActiveGymn() {
				
				return gyms.values().stream()
						.max((x,y)-> Integer.compare(x.getLessonssize(), y.getLessonssize())).map(x->x.getName()).get();
				
	}
	
	public Map<String, Integer> totalLessonsPerGymn() {		
		return gyms.values().stream().collect(
				Collectors.toMap(x->x.getName(), x->x.getLessonssize()));

	}
	
	public SortedMap<Integer, List<String>> slotsPerNofParticipants(String gymnname) throws FitException{

		 if(!gyms.containsKey(gymnname)) throw new FitException();
		 return	 gyms.get(gymnname).getSlots().entrySet().stream().collect( 
				 Collectors.groupingBy(
				 x->x.getValue().getCurrentattendee(),
				 ()-> new TreeMap<Integer, List<String>>(Comparator.naturalOrder()),
				 Collectors.mapping(y->
				(Integer.toString(y.getKey().getDay())+ "." + Integer.toString( y.getKey().getStart()))
				 ,
				Collectors.toList()))
				 );
	}
	

	
	
	
	


}
