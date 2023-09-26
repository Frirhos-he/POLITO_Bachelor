package sports;

import java.util.ArrayList;
import java.util.List;

public class Category {

	private String name;
	private  List<Activity> activities = new ArrayList<>();
	private  List<Product> products = new ArrayList<>();
	
	
	public Category(String name, List<Activity> activities) {
		this.name = name;
		this.activities = activities;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Activity> getActivities() {
		return activities;
	}
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
