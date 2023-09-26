package it.polito.oop.elective;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Course {

	private String name;
	private int availablePositions;
	private Map<Integer, Long> preferences = new HashMap<>();
	private List<Student> sub =  new ArrayList<>();
	
	public Course(String name, int availablePositions) {

		this.name = name;
		this.availablePositions = availablePositions;
		for(int i=1; i<4; i++) {
			preferences.put(i, 0L);
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAvailablePositions() {
		return availablePositions;
	}
	public void setAvailablePositions(int availablePositions) {
		this.availablePositions = availablePositions;
	}
	public Map<Integer, Long> getPreferences() {
		return preferences;
	}
	public void setPreferences(Map<Integer, Long> preferences) {
		this.preferences = preferences;
	}
	public List<Student> getSub() {
		return sub;
	}
	public List<String> getsubString(){
		return sub.stream().sorted(Comparator.comparing(Student::getGradeAverage).reversed()).map(x->x.getId()).collect(Collectors.toList());
	}
	public void setSub(List<Student> sub) {
		this.sub = sub;
	}

	

	

}
