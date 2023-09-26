package fit;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Gym {

	private String name;
	private Map<Slot, Lesson> slots = new HashMap<>();
	
	public Gym(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<Slot, Lesson> getSlots() {
		return slots;
	}

	public void setSlots(Map<Slot, Lesson> slots) {
		this.slots = slots;
	}

	@Override
	public String toString() {
		return "Gym [name=" + name + ", slots=" + slots + "]";
	}


	public Integer getLessonssize() {
		return slots.size();
	}
	

}
