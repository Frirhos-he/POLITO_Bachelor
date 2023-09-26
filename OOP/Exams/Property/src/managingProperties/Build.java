package managingProperties;

import java.util.HashMap;
import java.util.Map;

public class Build {

	private String name;
	private Integer apartments;
	private Map<Integer ,Apartment> apartmentsMap = new HashMap<>();
	
	
	public Build(String name, Integer floors) {
	
		this.name = name;
		this.apartments = floors;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getApartments() {
		return apartments;
	}
	public void setApartments(Integer apartments) {
		this.apartments = apartments;
	}
	public Map<Integer, Apartment> getApartmentsMap() {
		return apartmentsMap;
	}
	public void setApartmentsMap(Map<Integer, Apartment> apartmentsMap) {
		this.apartmentsMap = apartmentsMap;
	}
	

}
