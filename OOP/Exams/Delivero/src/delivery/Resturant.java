package delivery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Resturant {

	private String name;
	private String category;
	private Map<String, Float> dishes = new HashMap<>();
	private Map<Integer, Order> orders = new HashMap<>();
	private List<Integer> ratings = new ArrayList<>();
	
	public Resturant(String name, String category) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.category = category;
	}

	public float AvgRate() {
		float sum= 0;
		if(ratings.size() == 0) return -1;
		for(Integer tmp: ratings) {
			sum+=tmp;
		}
		sum = sum/ratings.size();
		return sum;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Map<String, Float> getDishes() {
		return dishes;
	}

	public void setDishes(Map<String, Float> dishes) {
		this.dishes = dishes;
	}
	
	public List<String> byPrice (float min, float max){
		
	
			return dishes.entrySet().stream().
			filter(x-> ( (x.getValue()>=min ) && (x.getValue()<=max ) )).map(x->x.getKey()).
			collect(Collectors.toList());
					
	}
	
	public Boolean isPrice (float min, float max) {
		if(dishes.entrySet().stream().anyMatch(x-> ( (x.getValue()>=min ) && (x.getValue()<=max ) ))) return true;
		else return false;
	}

	public Map<Integer, Order> getOrders() {
		return orders;
	}

	public void setOrders(Map<Integer, Order> orders) {
		this.orders = orders;
	}

	public List<Integer> getRatings() {
		return ratings;
	}

	public void setRatings(List<Integer> ratings) {
		this.ratings = ratings;
	}



}
