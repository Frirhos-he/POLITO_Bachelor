package it.polito.oop.vaccination;

import java.util.LinkedList;
import java.util.List;

public class Day {

	private Integer hour;
	
	public Day(Integer day) {
		// TODO Auto-generated constructor stub
		this.hour = day;
		
		
	}

	public Integer getDay() {
		return hour;
	}

	public void setDay(Integer day) {
		this.hour = day;
	}

	public List<String> hours(){
		
		List<String> element = new LinkedList<>();
		
		for(int i = 9; i < hour+9; ++i ) {
			
			for(int j = 0; j< 60; j= j+15) {

				element.add(String.format("%02d:%02d", i,j));
				System.out.println(element);
			}
		}
		return element;
		
	}
	
}
