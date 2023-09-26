package delivery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import delivery.Delivery.OrderStatus;

public class Order {

	private Integer orderID;
	private Customer customer;
	private Map<String, Integer> menus = new HashMap<>();
	private Map<String, Menu> menusD = new HashMap<>();
	public OrderStatus current = OrderStatus.NEW;
	
	public Order(Integer orderID, Customer customer) {

		this.orderID = orderID;
		this.customer = customer;
		
	}
	public Integer getOrderID() {
		return orderID;
	}
	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Map<String, Integer> getMenus() {
		return menus;
	}
	public void setMenus(Map<String, Integer> menus) {
		this.menus = menus;
	}
	public Map<String, Menu> getMenusD() {
		return menusD;
	}
	public void setMenusD(Map<String, Menu> menusD) {
		this.menusD = menusD;
	}

	public double totalPrice() {
		
		Double sum = 0.0D;
		for( Map.Entry<String, Integer> tmp: menus.entrySet()) {
			
			sum= sum + (tmp.getValue()*(menusD.get(tmp.getKey()).getPrice()));
			
		}

		return sum;
	}
	public OrderStatus getCurrent() {
		return current;
	}
	public void setCurrent(OrderStatus current) {
		this.current = current;
	}
	
	public Integer timeNeeded() {
		
		Integer sum = 5 + 15 + menusD.values().stream().map(x->x.getPrepTime()).reduce(0, (x,y) -> Integer.max(x, y));
		
		return sum;
	}


}
