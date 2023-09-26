package diet;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

import diet.Restaurant.Opening;

/**
 * Represents the main class in the
 * take-away system.
 * 
 * It allows adding restaurant, users, and creating orders.
 *
 */
public class Takeaway {

	 private SortedMap<String, Restaurant> mapResturant = new TreeMap<>();
	 private SortedMap<String, User> mapUser = new TreeMap<>();
	/**
	 * Adds a new restaurant to the take-away system
	 * 
	 * @param r the restaurant to be added
	 */
	public void addRestaurant(Restaurant r) {
		
		mapResturant.put(r.getName(), r);
		
		
	}
	
	/**
	 * Returns the collections of restaurants
	 * 
	 * @return collection of added restaurants
	 */
	public Collection<String> restaurants() {
		
		return (Collection<String>) mapResturant.keySet(); //safe cast
	}
	
	/**
	 * Define a new user
	 * 
	 * @param firstName first name of the user
	 * @param lastName  last name of the user
	 * @param email     email
	 * @param phoneNumber telephone number
	 * @return
	 */
	public User registerUser(String firstName, String lastName, String email, String phoneNumber) {
		
		User tmp = new User(firstName, lastName, email, phoneNumber);
		
		mapUser.put(lastName + " " + firstName ,tmp); //already ordered by key
		
		return tmp;
	}
	
	/**
	 * Gets the collection of registered users
	 * 
	 * @return the collection of users
	 */
	public Collection<User> users(){
		return mapUser.values();
	}
	
	/**
	 * Create a new order by a user to a given restaurant.
	 * 
	 * The order is initially empty and is characterized
	 * by a desired delivery time. 
	 * 
	 * @param user				user object
	 * @param restaurantName	restaurant name
	 * @param h					delivery time hour
	 * @param m					delivery time minutes
	 * @return
	 */
	public Order createOrder(User user, String restaurantName, int h, int m) {
		Order result;
		if (mapUser.containsValue(user) == true) { //if it is a registered user
		result = mapResturant.get(restaurantName).addOrder(user.getFirstName()+ " " + user.getLastName(), h, m);
		return result;
		} else  return null;
	}
	
	
	
	/**
	 * Retrieves the collection of restaurant that are open
	 * at the given time.
	 * 
	 * @param time time to check open
	 * 
	 * @return collection of restaurants
	 */
	public Collection<Restaurant> openedRestaurants(String time){
		
		Boolean flag = false;
		SortedMap<String, Restaurant> result = new TreeMap<>();
		for(Restaurant element: mapResturant.values()) {
			
			flag = false;
			for(Opening hour: element.getOpening().values() ) {
				
				if(hour.close.compareTo(time) >= 0 && hour.open.compareTo(time) <= 0) {
					flag = true;
					break;
				}	

			
			}
			if(flag == true) {
				result.put(element.getName(),element);
			}
			
		}
				
		return result.values();
	}

	
	
}
