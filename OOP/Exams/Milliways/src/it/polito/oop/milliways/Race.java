package it.polito.oop.milliways;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Race {
    
	private String name;
	private List<String> requirments = new ArrayList<>();
	
	public Race(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
		
	}

	public void addRequirement(String requirement) throws MilliwaysException {
		
		if(this.requirments.contains(requirement)) throw new MilliwaysException();
		this.requirments.add(requirement);
		
	}
	
	public List<String> getRequirements() {
        return this.requirments.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
	}
	
	public String getName() {
        return this.name;
	}
}
