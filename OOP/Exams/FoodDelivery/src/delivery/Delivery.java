package delivery;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Delivery {
	
	private Integer customerID = 0;
	private Integer orderID = 0;
	
	private Map<Integer, Customer> customers = new HashMap<>();
	private Map<String, Menu> menus = new HashMap<>();
	private List<String> registeredNumb = new ArrayList<>();
	private Map<Integer, Order> orders = new HashMap<>();
    
    public static enum OrderStatus { NEW, CONFIRMED, PREPARATION, ON_DELIVERY, DELIVERED } 
    
    /**
     * Creates a new customer entry and returns the corresponding unique ID.
     * 
     * The ID for the first customer must be 1.
     * 
     * 
     * @param name name of the customer
     * @param address customer address
     * @param phone customer phone number
     * @param email customer email
     * @return unique customer ID (positive integer)
     */
    public int newCustomer(String name, String address, String phone, String email) throws DeliveryException {
    	
    	customerID++;
    	
    	if(this.registeredNumb.contains(phone)) throw new DeliveryException();
    		
    		Customer element = new Customer(customerID, name,  address,  phone,  email);
    		registeredNumb.add(phone);
    		customers.put(customerID, element);
    		
        return customerID;
    }
    
    /**
     * Returns a string description of the customer in the form:
     * <pre>
     * "NAME, ADDRESS, PHONE, EMAIL"
     * </pre>
     * 
     * @param customerId
     * @return customer description string
     */
    public String customerInfo(int customerId){
    	
    	Customer element = this.customers.get(customerID);
    	
        return element.infocust();
    }
    
    /**
     * Returns the list of customers, sorted by name
     * 
     */
    public List<String> listCustomers(){
        return customers.values().stream().map(x->x.infocust()).sorted(Comparator.naturalOrder()).collect(Collectors.toList());
    }
    
    /**
     * Add a new item to the delivery service menu
     * 
     * @param description description of the item (e.g. "Pizza Margherita", "Bunet")
     * @param price price of the item (e.g. 5 EUR)
     * @param category category of the item (e.g. "Main dish", "Dessert")
     * @param prepTime estimate preparation time in minutes
     */
    public void newMenuItem(String description, double price, String category, int prepTime){
    	
    	Menu element = new Menu( description,  price,  category,  prepTime);
        menus.put(description,element);
    	
    }
    
    /**
     * Search for items matching the search string.
     * The items are sorted by category first and then by description.
     * 
     * The format of the items is:
     * <pre>
     * "[CATEGORY] DESCRIPTION : PRICE"
     * </pre>
     * 
     * @param search search string
     * @return list of matching items
     */
    public List<String> findItem(String search){
    	
    	if(search.equals("")) return menus.values().stream().sorted(
				Comparator.comparing(Menu::getCategory)
				.thenComparing(x->x.getDescription())
				).map(x->x.representation()).collect(Collectors.toList());
    	else {
    		return menus.values().stream().filter( x->x.getDescription().toLowerCase().contains(search.toLowerCase())).sorted(
    				Comparator.comparing(Menu::getCategory)
    				.thenComparing(x->x.getDescription())
    				).map(x->x.representation())
    				.collect(Collectors.toList());
    	}

    }
	
    
    public List<String> findItemD(String search){
    	
    	if(search.equals("")) return menus.values().stream().sorted(
				Comparator.comparing(Menu::getCategory)
				.thenComparing(x->x.getDescription())
				).map(x->x.getDescription()).collect(Collectors.toList());
    	else {
    		return menus.values().stream().filter( x->x.getDescription().toLowerCase().contains(search.toLowerCase())).sorted(
    				Comparator.comparing(Menu::getCategory)
    				.thenComparing(x->x.getDescription())
    				).map(x->x.getDescription())
    				.collect(Collectors.toList());
    	}
    	
    }
    
    /**
     * Creates a new order for the given customer.
     * Returns the unique id of the order, a progressive
     * integer number starting at 1.
     * 
     * @param customerId
     * @return order id
     */
    public int newOrder(int customerId){
    	orderID++;
    	
    	Order element = new Order(orderID, customers.get(customerId));
    	orders.put(orderID, element);
    	customers.get(customerId).getOrders().put(orderID, element);
    	
    	
        return orderID;
    }
    
    /**
     * Add a new item to the order with the given quantity.
     * 
     * If the same item is already present in the order is adds the
     * given quantity to the current quantity.
     * 
     * The method returns the final total quantity of the item in the order. 
     * 
     * The item is found through the search string as in {@link #findItem}.
     * If none or more than one item is matched, then an exception is thrown.
     * 
     * @param orderId the id of the order
     * @param search the item search string
     * @param qty the quantity of the item to be added
     * @return the final quantity of the item in the order
     * @throws DeliveryException in case of non unique match or invalid order ID
     */
    public int addItem(int orderId, String search, int qty) throws DeliveryException {
    	
    	if(this.findItemD(search).size() != 1) throw new DeliveryException();
    	
    	if(!orders.containsKey(orderId)) throw new DeliveryException();
    	
    	if(orders.get(orderId).getMenus().containsKey(this.findItemD(search).get(0)) == true) {
    		orders.get(orderId).getMenus().replace(
    				this.findItemD(search).get(0),
    				orders.get(orderId).getMenus()
    				.get(this.findItemD(search).get(0)) + qty );
    	} else {
    		orders.get(orderId).getMenus().put(this.findItemD(search).get(0), qty);
    		
    		orders.get(orderId).getMenusD().put(this.findItemD(search).get(0), menus.get(this.findItemD(search).get(0)));
    	}

        return orders.get(orderId).getMenus().get(this.findItemD(search).get(0));
    }
    
    /**
     * Show the items of the order using the following format
     * <pre>
     * "DESCRIPTION, QUANTITY"
     * </pre>
     * 
     * @param orderId the order ID
     * @return the list of items in the order
     * @throws DeliveryException when the order ID in invalid
     */
    public List<String> showOrder(int orderId) throws DeliveryException {
    	
    	if(!orders.containsKey(orderId)) throw new DeliveryException();
    	
    	return 
    			orders.get(orderId).getMenus().entrySet().stream().map(x-> x.getKey() +", " + x.getValue()).collect(Collectors.toList());

    }
    
    /**
     * Retrieves the total amount of the order.
     * 
     * @param orderId the order ID
     * @return the total amount of the order
     * @throws DeliveryException when the order ID in invalid
     */
    public double totalOrder(int orderId) throws DeliveryException {
    	
    	if(!orders.containsKey(orderId)) throw new DeliveryException();
        return orders.get(orderId).totalPrice();
    }
    
    /**
     * Retrieves the status of an order
     * 
     * @param orderId the order ID
     * @return the current status of the order
     * @throws DeliveryException when the id is invalid
     */
    public OrderStatus getStatus(int orderId) throws DeliveryException {
    	
    	if(!orders.containsKey(orderId))throw new DeliveryException();
        return orders.get(orderId).getCurrent();
    }
    
    /**
     * Confirm the order. The status goes from {@code NEW} to {@code CONFIRMED}
     * 
     * Returns the delivery time estimate computed as the sum of:
     * <ul>
     * <li>start-up delay (conventionally 5 min)
     * <li>preparation time (max of all item preparation time)
     * <li>transportation time (conventionally 15 min)
     * </ul>
     * 
     * @param orderId the order id
     * @return delivery time estimate in minutes
     * @throws DeliveryException when order ID invalid to status not {@code NEW}
     */
    public int confirm(int orderId) throws DeliveryException {
    	
    	if(!orders.containsKey(orderID))throw new DeliveryException();
    	if(!orders.get(orderID).getCurrent().equals(OrderStatus.NEW)) throw new DeliveryException();
    	
    	orders.get(orderID).setCurrent(OrderStatus.CONFIRMED);
    	
        return orders.get(orderId).timeNeeded();
    }

    /**
     * Start the order preparation. The status goes from {@code CONFIRMED} to {@code PREPARATION}
     * 
     * Returns the delivery time estimate computed as the sum of:
     * <ul>
     * <li>preparation time (max of all item preparation time)
     * <li>transportation time (conventionally 15 min)
     * </ul>
     * 
     * @param orderId the order id
     * @return delivery time estimate in minutes
     * @throws DeliveryException when order ID invalid to status not {@code CONFIRMED}
     */
    public int start(int orderId) throws DeliveryException {
    	
    	if(!orders.containsKey(orderID))throw new DeliveryException();
    	if(!orders.get(orderID).getCurrent().equals(OrderStatus.CONFIRMED)) throw new DeliveryException();
    	
    	orders.get(orderID).setCurrent(OrderStatus.PREPARATION);
    	
    	int time = orders.get(orderId).timeNeeded()-5;
        return  time;
    }

    /**
     * Begins the order delivery. The status goes from {@code PREPARATION} to {@code ON_DELIVERY}
     * 
     * Returns the delivery time estimate computed as the sum of:
     * <ul>
     * <li>transportation time (conventionally 15 min)
     * </ul>
     * 
     * @param orderId the order id
     * @return delivery time estimate in minutes
     * @throws DeliveryException when order ID invalid to status not {@code PREPARATION}
     */
    public int deliver(int orderId) throws DeliveryException {
    	
    	if(!orders.containsKey(orderID))throw new DeliveryException();
    	if(!orders.get(orderID).getCurrent().equals(OrderStatus.PREPARATION)) throw new DeliveryException();
    	
    	orders.get(orderID).setCurrent(OrderStatus.ON_DELIVERY);
    	
    	
        return 15;
    }
    
    /**
     * Complete the delivery. The status goes from {@code ON_DELIVERY} to {@code DELIVERED}
     * 
     * 
     * @param orderId the order id
     * @return delivery time estimate in minutes
     * @throws DeliveryException when order ID invalid to status not {@code ON_DELIVERY}
     */
    public void complete(int orderId) throws DeliveryException {
    	if(!orders.containsKey(orderID))throw new DeliveryException();
    	if(!orders.get(orderID).getCurrent().equals(OrderStatus.ON_DELIVERY)) throw new DeliveryException();
    	
    	orders.get(orderID).setCurrent(OrderStatus.DELIVERED);
    	
    	
    }
    
    /**
     * Retrieves the total amount for all orders of a customer.
     * 
     * @param customerId the customer ID
     * @return total amount
     */
    public double totalCustomer(int customerId){
    	
    	
    	return customers.get(customerId).getOrders().values().
    			
    			stream().filter(x-> !x.getCurrent().equals(OrderStatus.NEW)).mapToDouble(x->x.totalPrice()).sum();
    }
    
    /**
     * Computes the best customers by total amount of orders.
     *  
     * @return the classification
     */
    public SortedMap<Double,List<String>> bestCustomers(){
    	
    	return orders.values().stream().filter(x-> !x.getCurrent().equals(OrderStatus.NEW)).
    	map(x->x.getCustomer()).distinct().collect(
    			
    				Collectors.toMap( x->x.infocust(), x->x.totalCustomer())).
    					entrySet().stream().collect(Collectors.groupingBy(
    						x->x.getValue(),
    						()-> new TreeMap<Double,List<String>>(Comparator.reverseOrder()),
    						Collectors.mapping(Map.Entry::getKey, Collectors.toList())
    						));
    }
    
// NOT REQUIRED
//
//    /**
//     * Computes the best items by total amount of orders.
//     *  
//     * @return the classification
//     */
//    public List<String> bestItems(){
//        return null;
//    }
//    
//    /**
//     * Computes the most popular items by total quantity ordered.
//     *  
//     * @return the classification
//     */
//    public List<String> popularItems(){
//        return null;
//    }

}
