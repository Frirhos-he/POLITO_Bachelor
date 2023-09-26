package warehouse;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Product {

	private String code;
	private String description;
	private Integer quantity = 0;
	private Map<String, Supplier> suppliers = new HashMap<>();
	private Map<String, Order> ordered = new HashMap<>();
//

	public String getCode(){

		return code;
	}

	public Product(String code, String description) {
	this.code = code;
	this.description = description;
}

	public String getDescription(){
	
		return description;
	}
	
	public void setQuantity(int quantity){

		this.quantity = quantity;
		
	}

	public void decreaseQuantity(){

		this.quantity= this.quantity-1;
		
	}

	public int getQuantity(){

		return this.quantity;
	}

	public List<Supplier> suppliers(){
	
		return suppliers.values().stream().sorted(Comparator.comparing(x->x.getNome())).collect(Collectors.toList());
	}

	public List<Order> pendingOrders(){
		
		 return this.ordered.values().stream().filter(x->x.delivered() == false).
				sorted(Comparator.comparing(Order::getQuantity).reversed()).collect(Collectors.toList());

	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(Map<String, Supplier> suppliers) {
		this.suppliers = suppliers;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Map<String, Order> getOrdered() {
		return ordered;
	}

	public void setOrdered(Map<String, Order> ordered) {
		this.ordered = ordered;
	}
}
