package delivery;

import java.util.HashMap;
import java.util.Map;

public class Customer {
	
	 private Integer customerID;
	 private String name;
	 private String address;
	 private String phone;
	 private String email;
	 private Map<Integer, Order> orders = new HashMap<>();
	 
	 
	public Customer(Integer customerID, String name, String address, String phone, String email) {
		this.customerID = customerID;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
	public Integer getCustomerID() {
		return customerID;
	}
	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String infocust() {
		return this.getName() +", " + this.getAddress() +", "+ this.getPhone() +", " +this.getEmail();
	}
	public Map<Integer, Order> getOrders() {
		return orders;
	}
	public void setOrders(Map<Integer, Order> orders) {
		this.orders = orders;
	}

	public Double totalCustomer() {
		Double sum = 0.0D;
		for( Order tmp: orders.values()) {
			
			sum= sum + tmp.totalPrice();
			
		}
		return sum;
	}

}
