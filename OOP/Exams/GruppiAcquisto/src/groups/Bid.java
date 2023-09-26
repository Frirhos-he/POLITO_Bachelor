package groups;

public class Bid {

	private Group group ;
	private Supplier supplier;
	private Integer price;
	
	
	public Group getGroup() {
		return group;
	}
	public Bid(Group group, Supplier supplier, Integer price) {

		this.group = group;
		this.supplier = supplier;
		this.price = price;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}


}
