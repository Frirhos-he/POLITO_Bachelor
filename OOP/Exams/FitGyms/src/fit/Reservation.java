package fit;

public class Reservation {

	private Customer customer;
	private Gym gym;
	private Slot slot;

	
	public Reservation(Customer customer, Gym gym, Slot slot) {
		this.customer = customer;
		this.gym = gym;
		this.slot = slot;
	}

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Gym getGym() {
		return gym;
	}
	public void setGym(Gym gym) {
		this.gym = gym;
	}

	public Slot getSlot() {
		return slot;
	}
	public void setSlot(Slot slot) {
		this.slot = slot;
	}



}
