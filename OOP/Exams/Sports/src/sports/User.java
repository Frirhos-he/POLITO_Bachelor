package sports;

import java.util.ArrayList;
import java.util.List;

public class User {

	private List<Rate> ratings = new ArrayList<>();
	private String name;
	
	
	public User(String userName) {
		// TODO Auto-generated constructor stub
		this.name = userName;
	}



	public void setRatings(List<Rate> ratings) {
		this.ratings = ratings;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public List<Rate> getRatings() {
		return ratings;
	}

}
