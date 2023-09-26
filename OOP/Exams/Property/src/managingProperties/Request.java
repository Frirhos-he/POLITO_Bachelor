package managingProperties;

public class Request {

	public enum Status{
		Pending, Assigned, Completed}
	
	
	private Integer id;
	private Owner owner;
	private Apartment apartment;
	private Professional professional;
	private Status status = Status.Pending;
	private Worker worker;
	private Integer costs;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public Apartment getApartment() {
		return apartment;
	}
	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}
	public Request(Integer id, Owner owner, Apartment apartment, Professional professional) {
		this.id = id;
		this.owner = owner;
		this.apartment = apartment;
		this.professional = professional;
	}
	public Professional getProfessional() {
		return professional;
	}
	public void setProfessional(Professional professional) {
		this.professional = professional;
	}

	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Worker getWorker() {
		return worker;
	}
	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	public Integer getCosts() {
		return costs;
	}
	public void setCosts(Integer costs) {
		this.costs = costs;
	}
	

}
