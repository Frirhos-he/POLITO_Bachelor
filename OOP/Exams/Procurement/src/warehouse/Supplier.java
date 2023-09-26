package warehouse;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Supplier {

	private String code;
	private String name;
	private Map<String, Product> products = new HashMap<>();
	private Map<String, Order> ordered = new HashMap<>();

	public Supplier(String code, String name) {

		this.code = code;
		this.name = name;
	}

	public String getCodice(){

		return this.code;
	}

	public String getNome(){

		return this.name;
	}
	
	public void newSupply(Product product){

		
		this.products.put(product.getCode(), product);
		product.getSuppliers().put(code, this);
		
	}
	
	public List<Product> supplies(){

		return products.values().stream().sorted(Comparator.comparing(x->x.getDescription())).collect(Collectors.toList());
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Product> getProducts() {
		return products;
	}

	public void setProducts(Map<String, Product> products) {
		this.products = products;
	}

	public Map<String, Order> getOrdered() {
		return ordered;
	}

	public void setOrdered(Map<String, Order> ordered) {
		this.ordered = ordered;
	}
}
