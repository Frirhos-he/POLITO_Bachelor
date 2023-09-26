package warehouse;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Warehouse {
	
	private Map<String, Product> products = new HashMap<>();
	private Map<String, Supplier> suppliers = new HashMap<>();
	private Map<String, Order> orders = new HashMap<>();
	private Integer counterOrder = 0;

	
	public Product newProduct(String code, String description){
	
		
		Product element = new Product(code, description);
		products.put(code, element);
		
		
		return element;
	}
	
	public Collection<Product> products(){
		
		return this.products.values();
	}

	public Product findProduct(String code){
	
		return this.products.get(code);
	}

	public Supplier newSupplier(String code, String name){
			
		Supplier element = new Supplier(code, name);
		suppliers.put(code, element);
		
		
		return element;
	}
	
	public Supplier findSupplier(String code){

		return suppliers.get(code);
	}

	public Order issueOrder(Product prod, int quantity, Supplier supp)
		throws InvalidSupplier {

		if(!supp.getProducts().values().contains(prod)) throw new InvalidSupplier();
		
		counterOrder++;
		String id = "ORD"+counterOrder;
		Order element = new Order(id,prod,quantity,supp);
		orders.put(id, element);
		prod.getOrdered().put(id, element);
		supp.getOrdered().put(id, element);
		
		
		return element;
	}

	public Order findOrder(String code){
		return orders.get(code);
	}
	
	public List<Order> pendingOrders(){
		return this.orders.values().stream().filter(x->x.delivered() == false).
				sorted(Comparator.comparing(x->x.getProd().getCode())).collect(Collectors.toList());
	}

	public Map<String,List<Order>> ordersByProduct(){
	    return orders.values().stream().collect(Collectors.groupingBy(
	    		
	    		x-> x.getProd().getCode(),
	    		() -> new HashMap<String, List<Order>>(),
	    		Collectors.mapping(x->x, Collectors.toList())	
	    			)
	    		);
	}
	
	public Map<String,Long> orderNBySupplier(){
	    return orders.values().stream().collect(Collectors.groupingBy(
	    		
	    		
	    		x->x.getSupp().getName(),
	    		()-> new TreeMap<String, Long>(Comparator.naturalOrder()),
	    		Collectors.counting()
	    		)
	    		);
	}
	
	public List<String> countDeliveredByProduct(){
	    return orders.values().stream().filter(x->x.delivered() == true).collect(Collectors.groupingBy(
	    		
	    		x->x.getProd().getCode(),
	    		Collectors.counting()
	    		)).entrySet().stream().sorted(Comparator.comparing(Map.Entry<String, Long>::getValue).reversed())
	    		.map(x->x.getKey() + " - " + x.getValue())
	    		.collect(Collectors.toList());
	}
}
