package sports;

import java.util.ArrayList;
import java.util.List;

public class Product {

	private String name;
	private Activity activity;
	private Category category;
	private List<Rate> ratings = new ArrayList<>();
	
	public Product(String name, Activity activity, Category category) {

		this.name = name;
		this.activity = activity;
		this.category = category;
	}
	public double Avg() {
		
	return ratings.stream().map(x->x.getNumStars()).
			mapToDouble(x->x).average().orElse(0);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Rate> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rate> ratings) {
		this.ratings = ratings;
	}
	
	

}
