package it.polito.oop.elective;

import java.util.LinkedList;
import java.util.List;

public class Student {
	
	private String id;
	private double gradeAverage;
	private List<Course> preferences = new LinkedList<>();
	private Boolean assigned = false;
	private Course attending = null;
	private Integer choose = 0;
	
	
	public Student(String id, double gradeAverage) {
		this.id = id;
		this.gradeAverage = gradeAverage;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getGradeAverage() {
		return gradeAverage;
	}
	public void setGradeAverage(double gradeAverage) {
		this.gradeAverage = gradeAverage;
	}
	public List<Course> getPreferences() {
		return preferences;
	}
	public void setPreferences(List<Course> preferences) {
		this.preferences = preferences;
	}
	public Boolean getAssigned() {
		return assigned;
	}
	public void setAssigned(Boolean assigned) {
		this.assigned = assigned;
	}
	public Course getAttending() {
		return attending;
	}
	public void setAttending(Course attending) {
		this.attending = attending;
	}
	public Integer getChoose() {
		return choose;
	}
	public void setChoose(Integer choose) {
		this.choose = choose;
	}
	


}
