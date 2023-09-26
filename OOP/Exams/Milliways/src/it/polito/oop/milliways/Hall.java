package it.polito.oop.milliways;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Hall {
	
	private Integer id;
 	private List<String> facilities = new ArrayList<>();
 	private List<Party> parties = new ArrayList<>();

	public Hall(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void addFacility(String facility) throws MilliwaysException {
		
		if(this.facilities.contains(facility)) throw new MilliwaysException();
		else{
			this.facilities.add(facility);
		}
	}

	public List<String> getFacilities() {
        return this.facilities.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
	}
	
	int getNumFacilities(){
        return -1;
	}

	public boolean isSuitable(Party party) {
		return this.getFacilities().containsAll(party.getRequirements());
	}

	public List<Party> getParties() {
		return parties;
	}

	public void setParties(List<Party> parties) {
		this.parties = parties;
	}
	public Integer countFacility() {
		
		return this.getFacilities().size();
		
	}

}
