package it.polito.oop.vaccination;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Vaccines {
	
	private Map<String, Person> persons = new HashMap<>();
	private Map<String, Hub> hubs = new HashMap<>();
	private Map<Integer, Day> days = new HashMap<>();
	private List<Interval> intervals = new ArrayList<>();
	BiConsumer<Integer, String> listener;

    public final static int CURRENT_YEAR = java.time.LocalDate.now().getYear();

    // R1
    /**
     * Add a new person to the vaccination system.
     *
     * Persons are uniquely identified by SSN (italian "codice fiscale")
     *
     * @param first first name
     * @param last last name
     * @param ssn italian "codice fiscale"
     * @param y birth year
     * @return {@code false} if ssn is duplicate,
     */
    public boolean addPerson(String first, String last, String ssn, int y) {
    	
    	if(persons.containsKey(ssn)) return false;
    	else {
    	Person element = new Person(ssn, first, last, y);
    	persons.put(ssn, element);

    	return true;
    	}
    	
    }

    /**
     * Count the number of people added to the system
     *
     * @return person count
     */
    public int countPeople() {
        return this.persons.size();
    }

    /**
     * Retrieves information about a person.
     * Information is formatted as ssn, last name, and first name
     * separate by {@code ','} (comma).
     *
     * @param ssn "codice fiscale" of person searched
     * @return info about the person
     */
    public String getPerson(String ssn) {
    	
        return this.persons.get(ssn).print();
    }

    /**
     * Retrieves of a person given their SSN (codice fiscale).
     *
     * @param ssn "codice fiscale" of person searched
     * @return age of person (in years)
     */
    public int getAge(String ssn) {
    	
        return CURRENT_YEAR-this.persons.get(ssn).getY();
    }

    /**
     * Define the age intervals by providing the breaks between intervals.
     * The first interval always start at 0 (non included in the breaks)
     * and the last interval goes until infinity (not included in the breaks).
     * All intervals are closed on the lower boundary and open at the upper one.
     * <p>
     * For instance {@code setAgeIntervals(40,50,60)}
     * defines four intervals {@code "[0,40)", "[40,50)", "[50,60)", "[60,+)"}.
     *
     * @param brks the array of breaks
     */
    public void setAgeIntervals(int... brks) {
    	
    	Interval element = new Interval(0,brks[0],this.persons);
    	this.intervals.add(element);
    	
    	for(int i = 0; i< brks.length-1; ++i) {
    		
    		this.intervals.add(new Interval(brks[i],brks[i+1], this.persons));
    		
    	}
    	
    	element = new Interval(brks[brks.length-1], -1, this.persons);
    	this.intervals.add(element);
    }

    /**
     * Retrieves the labels of the age intervals defined.
     *
     * Interval labels are formatted as {@code "[0,10)"},
     * if the upper limit is infinity {@code '+'} is used
     * instead of the number.
     *
     * @return labels of the age intervals
     */
    public Collection<String> getAgeIntervals() {
    	
    	
        return this.intervals.stream().
   			 map(x->  "["+x.getLower()+","+ x.getUpper() +")").sorted().collect(Collectors.toList());
    	
    }

    /**
     * Retrieves people in the given interval.
     *
     * The age of the person is computed by subtracting
     * the birth year from current year.
     *
     * @param range age interval label
     * @return collection of SSN of person in the age interval
     */
    public Collection<String> getInInterval(String range) {
    	
    	if(this.intervals.stream().filter(x->x.getRange().equals(range)).count() == 0) return new ArrayList<String>();
    	else {
    		return this.intervals.stream().filter(x->x.getRange().equals(range)).map(x->x.getInIntervall())
    		.flatMap(x->x.stream()).collect(Collectors.toList());
    	}
        
    }

    // R2
    /**
     * Define a vaccination hub
     *
     * @param name name of the hub
     * @throws VaccineException in case of duplicate name
     */
    public void defineHub(String name) throws VaccineException {
    	
    	if(this.hubs.containsKey(name)) throw new VaccineException();
    	
    	Hub element = new Hub(name);
    	this.hubs.put(name, element);
    	
    	
    }

    /**
     * Retrieves hub names
     *
     * @return hub names
     */
    public Collection<String> getHubs() {
    	
        return this.hubs.keySet();
    }

    /**
     * Define the staffing of a hub in terms of
     * doctors, nurses and other personnel.
     *
     * @param name name of the hub
     * @param doctors number of doctors
     * @param nNurses number of nurses
     * @param other number of other personnel
     * @throws VaccineException in case of undefined hub, or any number of personnel not greater than 0.
     */
    public void setStaff(String name, int doctors, int nNurses, int other) throws VaccineException {
    	
    	if((doctors+ nNurses + other)<= 0) throw new VaccineException();
    	if(!this.hubs.containsKey(name)) throw new VaccineException();
    	
    	this.hubs.get(name).setDoctor(doctors);
    	this.hubs.get(name).setNurses(nNurses);
    	this.hubs.get(name).setOthers(other);
    	
    	
    }

    /**
     * Estimates the hourly vaccination capacity of a hub
     *
     * The capacity is computed as the minimum among
     * 10*number_doctor, 12*number_nurses, 20*number_other
     *
     * @param hub name of the hub
     * @return hourly vaccination capacity
     * @throws VaccineException in case of undefined or hub without staff
     */
    public int estimateHourlyCapacity(String hub) throws VaccineException {
    	
    	if(!this.hubs.containsKey(hub)) throw new VaccineException();
    	
    	Integer tmp = this.hubs.get(hub).hourlyCapacity();
    	if(tmp == -1) throw new VaccineException();
    	return tmp;
    	
 
    }

    // R3
    /**
     * Load people information stored in CSV format.
     *
     * The header must start with {@code "SSN,LAST,FIRST"}.
     * All lines must have at least three elements.
     *
     * In case of error in a person line the line is skipped.
     *
     * @param people {@code Reader} for the CSV content
     * @return number of correctly added people
     * @throws IOException in case of IO error
     * @throws VaccineException in case of error in the header
     */
    public long loadPeople(Reader people) throws IOException, VaccineException {
        // Hint:
        BufferedReader br = new BufferedReader(people);
        Integer counter = 0;
        String riga = null;
        int num = 1;
        
        
        try {
        riga=br.readLine();
        if(riga.isEmpty() || riga.isBlank()) throw new VaccineException();
        
       
        String[] arr = riga.split(",");
        if(arr.length != 4) {
        	if(listener!= null) listener.accept(num, riga);
        	throw new VaccineException();
        }
        
        if(!arr[0].equals("SSN") || !arr[1].equals("LAST") || !arr[2].equals("FIRST") || !arr[3].equals("YEAR") ) {
        	if(listener!= null) listener.accept(num, riga);
        	throw new IOException();}
        
		while( ( riga=br.readLine() ) != null) {
			num++;
			arr = riga.split(",");
			if(arr.length == 4)
				{
					if(!this.persons.containsKey(arr[0])) {

						this.addPerson(arr[2], arr[1], arr[0], Integer.parseInt(arr[3]));
						counter++;
					
					} else if(listener!= null) listener.accept(num, riga);
				} else if(listener!= null) listener.accept(num, riga);
		}
        } catch(IOException e) {
        	if(listener!= null) listener.accept(num, riga);
        	 throw new IOException();
        }
        
        
        
        br.close();
        return counter;
    }

    // R4
    /**
     * Define the amount of working hours for the days of the week.
     *
     * Exactly 7 elements are expected, where the first one correspond to Monday.
     *
     * @param hs workings hours for the 7 days.
     * @throws VaccineException if there are not exactly 7 elements or if the sum of all hours is less than 0 ore greater than 24*7.
     */
    public void setHours(int... hs) throws VaccineException {
    	int i = 0;
    	
    	if(hs.length != 7) throw new VaccineException();
    	
    	for (Integer tmp: hs) {
    		if(tmp> 12) throw new VaccineException();
    		
    		Day element = new Day(tmp);
    		
    		this.days.put(i, element);
    		i++;
    		
    	}
    	
    	
    }

    /**
     * Returns the list of standard time slots for all the days of the week.
     *
     * Time slots start at 9:00 and occur every 15 minutes (4 per hour) and
     * they cover the number of working hours defined through method {@link #setHours}.
     * <p>
     * Times are formatted as {@code "09:00"} with both minuts and hours on two
     * digits filled with leading 0.
     * <p>
     * Returns a list with 7 elements, each with the time slots of the corresponding day of the week.
     *
     * @return the list hours for each day of the week
     */
    public List<List<String>> getHours() {
    	

        return this.days.values().stream().map(x->x.hours()).collect(Collectors.toList());
    }

    /**
     * Compute the available vaccination slots for a given hub on a given day of the week
     * <p>
     * The availability is computed as the number of working hours of that day
     * multiplied by the hourly capacity (see {@link #estimateCapacity} of the hub.
     *
     * @return
     */
    public int getDailyAvailable(String hub, int day) {
    	
    	
        return hubs.get(hub).hourlyCapacity()*days.get(day).getDay();
    }

    /**
     * Compute the available vaccination slots for each hub and for each day of the week
     * <p>
     * The method returns a map that associates the hub names (keys) to the lists
     * of number of available hours for the 7 days.
     * <p>
     * The availability is computed as the number of working hours of that day
     * multiplied by the capacity (see {@link #estimateCapacity} of the hub.
     *
     * @return
     */
    public Map<String, List<Integer>> getAvailable() {
        return hubs.keySet().stream().collect(Collectors.toMap(x->x,x->days.keySet().stream().map(y->this.getDailyAvailable(x,y)).collect(Collectors.toList())));
    }

    /**s
     * Computes the general allocation plan a hub on a given day.
     * Starting with the oldest age intervals 40%
     * of available places are allocated
     * to persons in that interval before moving the the next
     * interval and considering the remaining places.propAllocatedAge
     * <p>
     * The returned value is the list of SSNs (codice fiscale) of the
     * persons allocated to that day
     * <p>
     * <b>N.B.</b> no particular order of allocation is guaranteed
     *
     * @param hub name of the hub
     * @param day day of week index (0 = Monday)
     * @return the list of daily allocations
     */
    public List<String> allocate(String hub, int day) {
    	Integer firstRun = 0;
    	Hub hubH = hubs.get(hub);
    	List<String> toBeprinted = new ArrayList<>();
    	if(hubH.allocated.isEmpty()==true) {
    	for(int i = 0; i<7; ++i) {
    		hubH.allocated.put(i, getDailyAvailable(hub, i));}}
    	loop: while(hubH.allocated.get(day)!=0 && firstRun<=1) {
    	for(Interval tmp: intervals.stream().sorted(Comparator.comparing(Interval::getLower).reversed()).collect(Collectors.toList())) {
    		int n = (int) (firstRun == 0 ? (hubH.allocated.get(day)*0.4):hubH.allocated.get(day));
    		Iterator<Person> itPer =tmp.getPersons().values().stream().filter(x->x.hub==null).limit(n).iterator();
    		while(itPer.hasNext() && hubH.allocated.get(day)!=0) {
    			Person next = itPer.next();
    			next.hub = hubH;toBeprinted.add(next.getSsn());
    			hubH.allocated.replace(day, hubH.allocated.get(day)-1);next.day = day;
    		} if(hubH.allocated.get(day)==0) break loop;}
    	 firstRun++;}return toBeprinted;
    }

    /**
     * Removes all people from allocation lists and
     * clears their allocation status
     */
    public void clearAllocation() {
    	this.persons.values().stream().forEach(x-> {x.day=-1; x.hub = null;});
		for(Hub hubH: hubs.values()) {
    	for(int i = 0; i<7; ++i) {
			hubH.allocated.remove(i);
			hubH.allocated.put(i, getDailyAvailable(hubH.getName(),i));}
    	}
    }

    /**
     * Computes the general allocation plan for the week.
     * For every day, starting with the oldest age intervals
     * 40% available places are allocated
     * to persons in that interval before moving the the next
     * interval and considering the remaining places.
     * <p>
     * The returned value is a list with 7 elements, one
     * for every day of the week, each element is a map that
     * links the name of each hub to the list of SSNs (codice fiscale)
     * of the persons allocated to that day in that hub
     * <p>
     * <b>N.B.</b> no particular order of allocation is guaranteed
     * but the same invocation (after {@link #clearAllocation}) must return the same
     * allocation.
     *
     * @return the list of daily allocations
     */
    public List<Map<String, List<String>>> weekAllocate() {
    	List<Map<String, List<String>>> tmp = new ArrayList<>();
    	java.util.stream.Stream.iterate(0, d -> d + 1).limit(7).forEach(x -> {tmp.add(new HashMap<>());
        hubs.values().forEach(h -> tmp.get(x).put(h.getName(), this.allocate(h.getName(), x)));});
    	return tmp;
    }

    // R5
    /**
     * Returns the proportion of allocated people
     * w.r.t. the total number of persons added
     * in the system
     *
     * @return proportion of allocated people
     */
    public double propAllocated() {
    	return (double)this.persons.values().stream().filter(x->x.hub!= null).count()/this.persons.size();
    }

    /**
     * Returns the proportion of allocated people
     * w.r.t. the total number of persons added
     * in the system, divided by age interval.
     * <p>
     * The map associates the age interval label
     * to the proportion of allocates people in that interval
     *
     * @return proportion of allocated people by age interval
     */
    public Map<String, Double> propAllocatedAge() {
        return this.intervals.stream().collect(Collectors.toMap(Interval::getRange,x->((double) x.getPersons().values().stream().filter(y->y.hub!= null).count()/(double) persons.size())));
    }

    /**
     * Retrieves the distribution of allocated persons
     * among the different age intervals.
     * <p>
     * For each age intervals the map reports the
     * proportion of allocated persons in the corresponding
     * interval w.r.t the total number of allocated persons
     *
     * @return
     */
    public Map<String, Double> distributionAllocated() {
  
    	return intervals.stream().collect(Collectors.toMap(Interval::getRange,x->((double) x.getPersons().values().stream().filter(y->y.hub!= null).count()/persons.values().stream().filter(y->y.hub!=null).count())));
    }

    // R6
    /**
     * Defines a listener for the file loading method.
     * The {@ accept()} method of the listener is called
     * passing the line number and the offending line.
     * <p>
     * Lines start at 1 with the header line.
     *
     * @param lst the listener for load errors
     */
    public void setLoadListener(BiConsumer<Integer, String> lst) {
    	listener = lst;
    }
}
