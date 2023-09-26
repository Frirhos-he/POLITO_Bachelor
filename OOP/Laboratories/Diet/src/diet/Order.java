package diet;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Represents an order in the take-away system
 */
public class Order {
	
	private Restaurant resturant;
	private String user;
	private String hour;
	private OrderStatus currentStatus;
	private PaymentMethod currentPayment;
	private SortedMap<String, Menusordered> menus = new TreeMap<>();
	
	public String getResturantName() {
		return resturant.getName();
	}

	public String getUser() {
		return user;
	}

	public String getHour() {
		return hour;
	}


	
	
	
	private static class Menusordered {
			Menu menu;
			int quantity;
			public Menusordered(Menu menu, int quantity) {
				
				this.menu = menu;
				this.quantity = quantity;
			}
			
	}
	public Order( String user, String hour, Restaurant resturant) {


		this.user = user;
		this.hour = hour;
		this.currentStatus = OrderStatus.ORDERED;
		this.currentPayment = PaymentMethod.CASH;
		this.resturant = resturant;
	}


 
	/**
	 * Defines the possible order status
	 */
	public enum OrderStatus {
		ORDERED, READY, DELIVERED;
	}
	/**
	 * Defines the possible valid payment methods
	 */
	public enum PaymentMethod {
		PAID, CASH, CARD;
	}
		
	/**
	 * Total order price
	 * @return order price
	 */
	public double Price() {
		
		return -1.0;
	}
	
	/**
	 * define payment method
	 * 
	 * @param method payment method
	 */
	public void setPaymentMethod(PaymentMethod method) {
		this.currentPayment = method;
	}
	
	/**
	 * get payment method
	 * 
	 * @return payment method
	 */
	public PaymentMethod getPaymentMethod() {
		return currentPayment;
	}
	
	/**
	 * change order status
	 * @param newStatus order status
	 */
	public void setStatus(OrderStatus newStatus) {
		this.currentStatus = newStatus;
	}
	
	/**
	 * get current order status
	 * @return order status
	 */
	public OrderStatus getStatus(){
		
		return this.currentStatus;
	}
	
	/**
	 * Add a new menu with the relative order to the order.
	 * The menu must be defined in the {@link Food} object
	 * associated the restaurant that created the order.
	 * 
	 * @param menu     name of the menu
	 * @param quantity quantity of the menu
	 * @return this order to enable method chaining
	 */
	public Order addMenus(String menu, int quantity) {

		Menusordered element = new Menusordered(resturant.getMenu(menu), quantity);
		
		if(menus.containsKey(menu) == true) { //avoid override
			menus.get(menu).quantity = quantity;
		}
		else menus.put(menu, element);
		
		
		return this;
	}
	
	/**
	 * Converts to a string as:
	 * <pre>
	 * RESTAURANT_NAME, USER_FIRST_NAME USER_LAST_NAME : DELIVERY(HH:MM):
	 * 	MENU_NAME_1->MENU_QUANTITY_1
	 * 	...
	 * 	MENU_NAME_k->MENU_QUANTITY_k
	 * </pre>
	 */
	@Override
	public String toString() {
		
		StringBuffer element;
		element = new StringBuffer();
		
		String header;
		header = resturant.getName() + ", " + user + " : " + "(" + hour +"):"; 
		
		element.append(header);
		
		for(Menusordered tmp: menus.values()) {
			
			element.append("\n\t");
			element.append(tmp.menu.getName() + "->"+ tmp.quantity);
			
		}
		
		return element.toString();
	}
	
}
