package diet;

public class Product implements NutritionalElement {

	private String name;
	private double calories;
	private double proteins;
	private double carbs;
	private double fat;

	
	public Product(String name, double calories, double proteins, double carbs, double fat) {

		this.name = name;
		this.calories = calories;
		this.proteins = proteins;
		this.carbs = carbs;
		this.fat = fat;
	}


	


	@Override
	public double getCalories() {
		// TODO Auto-generated method stub
		return calories;  //Kcal
	}


	@Override
	public double getProteins() {
		// TODO Auto-generated method stub
		return proteins;  //grams
	}


	@Override
	public double getCarbs() {
		// TODO Auto-generated method stub
		return carbs;  //grams
	}


	@Override
	public double getFat() {
		// TODO Auto-generated method stub
		return fat;  //grams
	}


	@Override
	public boolean per100g() {
		// TODO Auto-generated method stub
		return false;
	}





	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	

}
