package warehouse;

public class Order {

	private String id;
	private Product prod;
	private int quantity;
	private Supplier supp;
	private Boolean deliver = false;
	

	public Order(String id, Product prod, int quantity, Supplier supp) {
		this.id = id;
		this.prod = prod;
		this.quantity = quantity;
		this.supp = supp;
	}

	public String getCode(){

		return this.id;
	}
	
	

	public boolean delivered(){
		return deliver;
	}

	public void setDelivered() throws MultipleDelivery {
		
		if(this.deliver == true) throw new MultipleDelivery();
		
		prod.setQuantity(prod.getQuantity()+quantity);
		this.deliver = true;
	}
	
	public String toString(){
		return "Order "+this.id + " for "+ this.quantity + " of " +
				this.prod.getCode()+ " : " + this.prod.getDescription() + " from " + this.supp.getName();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Product getProd() {
		return prod;
	}

	public void setProd(Product prod) {
		this.prod = prod;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Supplier getSupp() {
		return supp;
	}

	public void setSupp(Supplier supp) {
		this.supp = supp;
	}
}
