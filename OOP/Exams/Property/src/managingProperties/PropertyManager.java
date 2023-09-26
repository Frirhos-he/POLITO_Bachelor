package managingProperties;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import managingProperties.Request.Status;

public class PropertyManager {

	private Map<String, Build> builds = new TreeMap<>();
	private Map<String, Owner> owners = new HashMap<>();
	private Map<String, Worker> workers = new HashMap<>();
	private Map<String, Apartment> apartments = new HashMap<>();
	private Map<String, Professional> professionals = new TreeMap<>();
	private Map<Integer, Request> requests = new TreeMap<>();
	
	private Integer requestNumber = 0;
	/**
	 * Add a new building 
	 */
	public void addBuilding(String building, int n) throws PropertyException {
		
		if(builds.containsKey(building)) throw new PropertyException();
		if(n > 100 || n < 1 ) throw new PropertyException();
		
		Build element = new Build(building, n);
		builds.put(building, element);
		
	}

	public void addOwner(String owner, String... apartments) throws PropertyException {
		
		if(owners.containsKey(owner)) throw new PropertyException();
		
		Owner element = new Owner(owner);
		owners.put(owner, element);
		
		for(String tmp: apartments) {
			
			String[] arr = tmp.split(":");

			if(!builds.containsKey(arr[0])) throw new PropertyException();
			else if(builds.get(arr[0]).getApartments()< Integer.parseInt(arr[1]) || Integer.parseInt(arr[1])<1) throw new PropertyException(); 
			
			if(builds.get(arr[0]).getApartmentsMap().containsKey(Integer.parseInt(arr[1]))) throw new PropertyException();
			
			Apartment apartment = new Apartment(Integer.parseInt(arr[1]),builds.get(arr[0]), tmp, element);
			builds.get(arr[0]).getApartmentsMap().put(Integer.parseInt(arr[1]),apartment);
			owners.get(owner).getApartments().put(Integer.parseInt(arr[1]),apartment);
			this.apartments.put(tmp,apartment);
			
		}
		
	}

	/**
	 * Returns a map ( number of apartments => list of buildings ) 
	 * 
	 */
	public SortedMap<Integer, List<String>> getBuildings() {
		
		return builds.values().stream()
				.collect(Collectors.groupingBy(
						
						Build::getApartments,
						()-> new TreeMap<Integer, List<String>>(Comparator.naturalOrder()),
						Collectors.mapping(Build::getName,Collectors.toList())
						));
				
				
	}

	public void addProfessionals(String profession, String... professionals) throws PropertyException {
			
		if(this.professionals.containsKey(profession)) throw new PropertyException();
		Professional element = new Professional(profession);
		this.professionals.put(profession, element);
		
		
		for(String tmp: professionals) {
			
			if(this.workers.containsKey(tmp)) throw new PropertyException();
			Worker worker = new Worker(tmp, element);
			workers.put(tmp, worker);
			this.professionals.get(profession).getWorkers().put(tmp, worker);
			
		}
		
	}

	/**
	 * Returns a map ( profession => number of workers )
	 *
	 */
	public SortedMap<String, Integer> getProfessions() {
		
		return this.professionals.values().stream().collect(
				Collectors.toMap(
						x->x.getName(),
						x->x.getWorkers().size(),
						(x,y)->x,
						TreeMap::new
						));

	}

	public int addRequest(String owner, String apartment, String profession) throws PropertyException {

		
		if(!this.owners.containsKey(owner) )throw new PropertyException();
		if(!this.apartments.containsKey(apartment)) throw new PropertyException();
		if(
				this.apartments.get(apartment).getOwner()!= null &&
				!this.apartments.get(apartment).getOwner().getName().equals(owner)) throw new PropertyException();
		if(!this.professionals.containsKey(profession)) throw new PropertyException();
		
		
		requestNumber++;
		
		Request element = new Request(requestNumber, 
				this.owners.get(owner),this.apartments.get(apartment), this.professionals.get(profession));
		requests.put(requestNumber, element);
		this.owners.get(owner).getRequests().put(requestNumber, element);
		this.professionals.get(profession).getRequests().put(requestNumber, element);
				
		return requestNumber;
	}

	public void assign(int requestN, String professional) throws PropertyException {
		
		if(!this.requests.containsKey(requestN)) throw new PropertyException();
		if(!this.requests.get(requestN).getStatus().equals(Status.Pending))
			throw new PropertyException();
		if(!this.workers.containsKey(professional))throw new PropertyException();
		if(!this.workers.get(professional).getProfession().equals(this.requests.get(requestN).getProfessional()))
			throw new PropertyException();
		this.requests.get(requestN).setWorker(this.workers.get(professional));
		this.requests.get(requestN).setStatus(Status.Assigned);
		
		
		
	}

	public List<Integer> getAssignedRequests() {
		
		return this.requests.values().stream().
				filter(x->x.getStatus().equals(Status.Assigned)).map(x->x.getId()).sorted().collect(Collectors.toList());
	}

	
	public void charge(int requestN, int amount) throws PropertyException {
		
		if(amount <0 || amount >1000) throw new PropertyException();
		if(!this.requests.containsKey(requestN))throw new PropertyException();
		if(!this.requests.get(requestN).getStatus().equals(Status.Assigned))throw new PropertyException();
		
		
		this.requests.get(requestN).setStatus(Status.Completed);
		this.requests.get(requestN).setCosts(amount);
		this.requests.get(requestN).getOwner().setCosts( amount + this.requests.get(requestN).getOwner().getCosts());
		
		if(!this.requests.get(requestN).getWorker().getCosts().containsKey(
				this.requests.get(requestN).getApartment().getBuild().getName()
				)) {
			this.requests.get(requestN).getWorker().getCosts().put(
					this.requests.get(requestN).getApartment().getBuild().getName(), amount);
		}
			else {
				
				this.requests.get(requestN).getWorker().getCosts().replace(
						this.requests.get(requestN).getApartment().getBuild().getName(), amount +
			this.requests.get(requestN).getWorker().getCosts().get(	this.requests.get(requestN).getApartment().getBuild().getName()));
						
					
			}
		}
	

	/**
	 * Returns the list of request ids
	 * 
	 */
	public List<Integer> getCompletedRequests() {
		
		return requests.values().stream().
				filter(x->x.getStatus().equals(Status.Completed)).map(x->x.getId()).sorted().collect(Collectors.toList());
	}
	
	/**
	 * Returns a map ( owner => total expenses )
	 * 
	 */
	public SortedMap<String, Integer> getCharges() {
		
		return this.owners.values().stream().filter(x->x.getCosts()!= 0).
				sorted(Comparator.comparing(x->x.getName())).collect(
				Collectors.toMap(
						
						x->x.getName(),
						x->x.getCosts(),
						(x,y)->x,
						TreeMap::new
						));
	}

	/**
	 * Returns the map ( building => ( profession => total expenses) ).
	 * Both buildings and professions are sorted alphabetically
	 * 
	 */
	public SortedMap<String, Map<String, Integer>> getChargesOfBuildings() {
		
		return this.requests.values().stream().filter(x->x.getStatus().equals(Status.Completed)).collect(Collectors.groupingBy(
				
				x->x.getApartment().getBuild().getName(),
				()->new TreeMap<String, Map<String, Integer>>(),
				Collectors.toMap(
						x->x.getWorker().getId(),
						x->x.getWorker().getCosts().get(x.getApartment().getBuild().getName()),
						(x,y)->x,
						TreeMap::new
						)
				
				));
	}

}
