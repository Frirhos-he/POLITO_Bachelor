package fit;

import java.util.List;

public class Lesson {
	
	private Gym gym;
	private String activity;
	private int maxattendees;
	private int currentattendee = 0;
	private Slot slot;
	private List<String> allowedinstructors;
	private String instructor = "0";
	
	public Lesson(Gym gym, String activity, int maxattendees, Slot slot, List<String> allowedinstructors) {

		this.gym = gym;
		this.activity = activity;
		this.maxattendees = maxattendees;
		this.slot = slot;
		this.allowedinstructors = allowedinstructors;
	}

	public Boolean isMax() {
		if(currentattendee == maxattendees) return true;
		else return false;
	}
	public Gym getGym() {
		return gym;
	}

	public void setGym(Gym gym) {
		this.gym = gym;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public int getMaxattendees() {
		return maxattendees;
	}

	public void setMaxattendees(int maxattendees) {
		this.maxattendees = maxattendees;
	}

	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}


	public int getCurrentattendee() {
		return currentattendee;
	}

	public void setCurrentattendee(int currentattendee) {
		this.currentattendee = currentattendee;
	}

	public List<String> getAllowedinstructors() {
		return allowedinstructors;
	}

	public void setAllowedinstructors(List<String> allowedinstructors) {
		this.allowedinstructors = allowedinstructors;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	
	
}
