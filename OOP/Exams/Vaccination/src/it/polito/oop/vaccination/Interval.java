package it.polito.oop.vaccination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Interval {
	
	private Integer lower;
	private Integer upper;
	private Map<String, Person> persons = new HashMap<>();
	
	
	public Interval(Integer lower, Integer upper, Map<String, Person> persons) {
	
		this.persons = persons;
		this.lower = lower;
		this.upper = upper;
	}
	public Integer getLower() {
		return lower;
	}
	public void setLower(Integer lower) {
		this.lower = lower;
	}
	public String getUpper() {
		if(upper == -1) return "+";
		else
			return upper.toString();
	}
	public void setUpper(Integer upper) {
		this.upper = upper;
	}
	public Map<String, Person> getPersons() {	
		Integer	current = java.time.LocalDate.now().getYear();	
		return this.persons.values().stream().filter(x->(upper==-1 ? (current-x.getY())>=lower:((current-x.getY())<upper&&(current-x.getY())>=lower))).collect(Collectors.toMap(Person::getSsn,x->x));}
	public void setPersons(Map<String, Person> persons) {
		this.persons = persons;
	}
	
	
	public List<String> getInIntervall(){
		
	Integer	current = java.time.LocalDate.now().getYear();
	if(upper == -1) {
		
		return this.persons.values().stream().filter(x->  (current-x.getY())>=lower)
		.map(x->x.getSsn()).collect(Collectors.toList());
	} else {
	return this.persons.values().stream().filter(x->(current-x.getY())<upper  &&  (current-x.getY())>=lower)
	.map(x->x.getSsn()).collect(Collectors.toList());
	}
	
		
	}
	public String getRange() {
		
		
		return "["+this.getLower()+ ","+ this.getUpper()+")";
	}

}
