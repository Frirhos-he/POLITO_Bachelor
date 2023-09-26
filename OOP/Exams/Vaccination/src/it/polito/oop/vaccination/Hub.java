package it.polito.oop.vaccination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Hub {

	
	private String name;
	private Integer doctor = 0;
	private Integer others = 0;
	private Integer nurses = 0;
	public java.util.Map<Integer,Integer> allocated = new java.util.LinkedHashMap<>();
	
	public Integer  hourlyCapacity() {
		
		if(doctor == 0 && others == 0 && nurses == 0) return -1;
		
		
		Integer comp1 = doctor*10;
		Integer comp2 = nurses*12;
		Integer comp3 = others*20;
		
		List<Integer> tmp = new ArrayList<>();
		tmp.add(comp1);
		tmp.add(comp2);
		tmp.add(comp3);
		Collections.sort(tmp);
		return tmp.get(0);
		
	}
	
	public Hub(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDoctor() {
		return doctor;
	}

	public void setDoctor(Integer doctor) {
		this.doctor = doctor;
	}

	public Integer getOthers() {
		return others;
	}

	public void setOthers(Integer others) {
		this.others = others;
	}

	public Integer getNurses() {
		return nurses;
	}

	public void setNurses(Integer nurses) {
		this.nurses = nurses;
	}

}
