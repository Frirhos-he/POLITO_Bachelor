package diet;
import static java.util.Comparator.comparing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import diet.Order.OrderStatus;

/**
 * Represents a restaurant in the take-away system
 *
 */

public class Restaurant {
	
	private Food food;
	private String name;
	private SortedMap <String, Opening> opening = new TreeMap<>();
	private Map <String, Menu> menu = new HashMap<>();
	private List <Order> order = new ArrayList<>(); 
	
	public static class Opening{
	
		 String open;
		 String close;
		public Opening(String op, String cl) {
			this.open = op;
			this.close = cl;
		}
		
	}
	
	
	public Order addOrder(String user, int h, int m) {
		
		String currentTime;
		Boolean flag = false;
		Boolean looped = true;
		
		
		if(h<10) {
			currentTime = "0" + h + ":" + m ;
		} else currentTime = h + ":" + m ;
		if(m == 0) currentTime += "0"; 
		
		for(Opening hour: opening.values() ) {
			
			if(hour.close.compareTo(currentTime) >= 0 && hour.open.compareTo(currentTime) <= 0) {
				flag = true;
				break;
			}
				
		}
		
			if(flag == false) {
				
				for(String hour: opening.keySet() ) { //opening hour
					
					if(hour.compareTo(currentTime) > 0) {
						
						currentTime = hour;
						looped = false;
						break;
					}
						
				}
				if(looped == true) currentTime = opening.firstKey(); //next day
			}
		
		Order tmp = new Order(user, currentTime,this);
		order.add(tmp);
		
		
		return tmp;
		
	}
	
	
	public SortedMap<String, Opening> getOpening() {
		return opening;
	}



	/**
	 * Constructor for a new restaurant.
	 * 
	 * Materials and recipes are taken from
	 * the food object provided as argument.
	 * 
	 * @param name	unique name for the restaurant
	 * @param food	reference food object
	 */
	public Restaurant(String name, Food food) {
		
		this.name = name;
		this.food = food;
		// TODO: implement constructor
	}
	
	/**
	 * gets the name of the restaurant
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Define opening hours.
	 * 
	 * The opening hours are considered in pairs.
	 * Each pair has the initial time and the final time
	 * of opening intervals.
	 * 
	 * for a restaurant opened from 8:15 until 14:00 and from 19:00 until 00:00, 
	 * is thoud be called as {@code setHours("08:15", "14:00", "19:00", "00:00")}.
	 * 
	 * @param hm a list of opening hours
	 */
	public void setHours(String ... hm) {
		
		for(int i = 0; i< hm.length; i = i+2) {
			Opening open = new Restaurant.Opening(hm[i], hm[i+1]);
			opening.put(hm[i], open); //put just start time
		}
				
	}
	
	public Menu getMenu(String name) {
		
	return menu.get(name);

	}
	
	/**
	 * Creates a new menu
	 * 
	 * @param name name of the menu
	 * 
	 * @return the newly created menu
	 */
	public Menu createMenu(String name) {

		Menu tmp = (Menu) food.createMenu(name);
		menu.put(name,tmp);
		return tmp;
	}

	/**
	 * Find all orders for this restaurant with 
	 * the given status.
	 * 
	 * The output is a string formatted as:
	 * <pre>
	 * Napoli, Judi Dench : (19:00):
	 * 	M6->1
	 * Napoli, Ralph Fiennes : (19:00):
	 * 	M1->2
	 * 	M6->1
	 * </pre>
	 * 
	 * The orders are sorted by name of restaurant, name of the user, and delivery time.
	 * 
	 * @param status the status of the searched orders
	 * 
	 * @return the description of orders satisfying the criterion
	 */
	public String ordersWithStatus(OrderStatus status) {
		
		StringBuffer element;
		
		element = new StringBuffer();
		
		Collections.sort(order, comparing( Order::getResturantName ).thenComparing( Order::getUser)
				.thenComparing(Order:: getHour));
		
		for(Order tmp: order) {
			if(tmp.getStatus() == status) {
			element.append(tmp);
			element.append("\n");
			}
		}
		return element.toString();
	}
}
