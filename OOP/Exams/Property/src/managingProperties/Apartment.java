package managingProperties;

public class Apartment {

	private Integer name;
	private Build build;
	private String originalName;
	private Owner owner;
	
	
	public Apartment(Integer name, Build build2, String tmp, Owner element) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.build = build2;
		this.setOriginalName(tmp);
		this.owner = element;
		
	}
	public Integer getName() {
		return name;
	}
	public void setName(Integer name) {
		this.name = name;
	}
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public Build getBuild() {
		return build;
	}
	public void setBuild(Build build) {
		this.build = build;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	
}
