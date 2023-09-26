package transactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Region {
	
	private String regionName;
	Map<String,Place> places = new TreeMap<>();
	Map<String,Carrier> carriers = new TreeMap<>();
	
	public Region(String regionName, Map<String, Place> place) {

		this.regionName = regionName;
		this.places = place;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}


	public Map<String, Carrier> getCarriers() {
		return carriers;
	}

	public void setCarriers(Map<String, Carrier> carriers) {
		this.carriers = carriers;
	}

	public Map<String, Place> getPlaces() {
		return places;
	}

	public void setPlaces(Map<String, Place> places) {
		this.places = places;
	}



}
