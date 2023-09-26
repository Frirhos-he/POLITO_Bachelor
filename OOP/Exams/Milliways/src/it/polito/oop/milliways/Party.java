package it.polito.oop.milliways;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Party {
	
	Map<Race, Integer> companions = new HashMap<>();
	Map<Integer, Hall> halls = new HashMap<>();
	
	

    public void addCompanions(Race race, int num) {
    	
    	if(companions.containsKey(race)) companions.replace(race, num + companions.get(race));
    	else {
    		companions.put(race, num);
    	}
	}

	public int getNum() {
        return companions.values().stream().mapToInt(x->x).sum();
	}

	public int getNum(Race race) {
	    return this.companions.get(race);
	}

	public List<String> getRequirements() {
        return this.companions.keySet().stream().
        		map(x->x.getRequirements()).
        		flatMap(x->x.stream()).distinct().
        		sorted(Comparator.naturalOrder()).collect(Collectors.toList());
	}

    public Map<String,Integer> getDescription(){
        return this.companions.entrySet().stream().
        		collect(Collectors.toMap(x->x.getKey().getName(), x->x.getValue()));
    }

	public Map<Race, Integer> getCompanions() {
		return companions;
	}

	public void setCompanions(Map<Race, Integer> companions) {
		this.companions = companions;
	}

	public Map<Integer, Hall> getHalls() {
		return halls;
	}

	public void setHalls(Map<Integer, Hall> halls) {
		this.halls = halls;
	}

}
