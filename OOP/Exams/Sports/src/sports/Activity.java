package sports;

import java.util.ArrayList;
import java.util.List;

public class Activity {

	private String name;
	private List<Category> categories = new ArrayList<>();
	private  List<Product> products = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Activity(String name) {
		this.name = name;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public Double avgA() {
		return this.products.stream().map(x->x.getRatings()).
		flatMap(x->x.stream()).map(x->x.getNumStars()).mapToDouble(x->x).average().orElse(0);
	}


}
