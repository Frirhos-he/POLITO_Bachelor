package groups;

import java.util.HashMap;
import java.util.Map;

public class Product {

	private String name;
	private Map<String, Supplier> suppliers = new HashMap<>();
	
	
	public Product(String productTypeName) {
		// TODO Auto-generated constructor stub
		this.name = productTypeName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(Map<String, Supplier> suppliers) {
		this.suppliers = suppliers;
	}


}
