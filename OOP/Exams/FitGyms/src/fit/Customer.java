package fit;

import java.util.HashMap;
import java.util.Map;

public class Customer {

	private Integer id;
	private String name;
	private Map<Slot, Reservation> reservations = new HashMap<>();
	
	public Customer(Integer globalC, String name) {
		// TODO Auto-generated constructor stub
		this.id = globalC;
		this.name = name;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<Slot, Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Map<Slot, Reservation> reservations) {
		this.reservations = reservations;
	}

}
