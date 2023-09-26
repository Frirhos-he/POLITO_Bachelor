package it.polito.oop.milliways;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class Restaurant {

	private Map<String,Race> races = new HashMap<>();
	private Party party;
	private Map<Integer,Hall> halls = new LinkedHashMap<>();
	
	
	
    public Restaurant() {
	}
	
	public Race defineRace(String name) throws MilliwaysException{
		
		if(races.containsKey(name)) throw new MilliwaysException();
		
		Race element = new Race(name);
		races.put(name, element);
		
		
	    return element;
	}
	
	public Party createParty() {
		
		party = new Party();
		
		
	    return party;
	}
	
	public Hall defineHall(int id) throws MilliwaysException{
		
		if(halls.containsKey(id)) throw new MilliwaysException();
		
		Hall element = new Hall(id);
		this.halls.put(id, element);
		
		
	    return element;
	}

	public List<Hall> getHallList() {
		return this.halls.values().stream().collect(Collectors.toList());
	}

	public Hall seat(Party party, Hall hall) throws MilliwaysException {
		
		if(!hall.isSuitable(party)) throw new MilliwaysException();
		
		hall.getParties().add(party);
		party.getHalls().put(hall.getId(),hall);
		
		
        return hall;
	}

	public Hall seat(Party party) throws MilliwaysException {
		
		if(!this.getHallList().stream().filter(x->x.isSuitable(party)).findFirst().isPresent()) throw new MilliwaysException();
		
		Hall element = this.getHallList().stream().filter(x->x.isSuitable(party)).findFirst().get();
		element.getParties().add(party);
		party.getHalls().put(element.getId(), element);
		
		
		
		
        return element;
	}

	public Map<Race, Integer> statComposition() {
		
		return this.halls.values().stream().map(x->x.getParties()).flatMap(x->x.stream()).map(x->x.getCompanions()).flatMap(x->x.entrySet().stream())
		.collect(Collectors.groupingBy(
				
				x->x.getKey(),
				HashMap::new,
				Collectors.summingInt(x->x.getValue())
					
				));
	}

	public List<String> statFacility() {
		
        return  halls.values().stream().map(x->x.getFacilities()).flatMap(x->x.stream()).collect(Collectors.groupingBy(
        		x->x,
        		Collectors.counting()
        		)).entrySet().stream().sorted(Comparator.comparing(Map.Entry<String, Long>::getValue)
                    .reversed().thenComparing(Map.Entry::getKey)).map(Map.Entry::getKey).collect(Collectors.toList());

	}
	
	public Map<Integer,List<Integer>> statHalls() {
		
		return halls.values().stream().sorted(Comparator.comparing(x->x.getId())).collect(Collectors.toMap(x->x.getId(),x->x.countFacility())
				)
		.entrySet().stream().collect(Collectors.groupingBy(
        		
        		x->x.getValue(),
        		()-> new TreeMap<Integer,List<Integer>>(Comparator.naturalOrder()),
        		Collectors.mapping(x->x.getKey(),Collectors.toList())));

        		
	}

}
