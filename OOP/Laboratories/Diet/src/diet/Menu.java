package diet;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a complete menu.
 * 
 * It can be made up of both packaged products and servings of given recipes.
 *
 */
public class Menu implements NutritionalElement {
	
	private String name;
	private Food food;
	private List<Recipe> recipeElement = new ArrayList<>();
	private List<Double> recipeDouble = new ArrayList<>();
	private List<Product> productElement = new ArrayList<>();
	
	
	private double calories = 0 ;
	private double proteins = 0;
	private double carbs = 0;
	private double fat = 0;

	
	 Menu(String name, Food food) {
			this.name = name;
			this.food = food;
		}


	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Adds a given serving size of a recipe.
	 * 
	 * The recipe is a name of a recipe defined in the
	 * {@Link Food} in which this menu has been defined.
	 * 
	 * @param recipe the name of the recipe to be used as ingredient
	 * @param quantity the amount in grams of the recipe to be used
	 * @return the same Menu to allow method chaining
	 */
	public Menu addRecipe(String recipe, double quantity) {
		
		Recipe element;
		Double doub = Double.valueOf(quantity);
		element = (Recipe)food.getRecipe(recipe);
		
		
		
		recipeElement.add(element);
		recipeDouble.add(doub);
		
		calories += element.getCalories(quantity);
		proteins += element.getProteins(quantity);
		carbs += element.getCarbs(quantity);
		fat += element.getFat(quantity);
		
		return this;
	}

	/**
	 * Adds a unit of a packaged product.
	 * The product is a name of a product defined in the
	 * {@Link Food} in which this menu has been defined.
	 * 
	 * @param product the name of the product to be used as ingredient
	 * @return the same Menu to allow method chaining
	 */
	public Menu addProduct(String product) {
		
		Product element;
		
		element = (Product)food.getProduct(product);

		productElement.add(element);
		
		calories += element.getCalories();
		proteins += element.getProteins();
		carbs += element.getCarbs();
		fat += element.getFat();
		
		
		return this;
	}

	/**
	 * Name of the menu
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Total KCal in the menu
	 */
	@Override
	public double getCalories() {
		
		return calories;
	}

	/**
	 * Total proteins in the menu
	 */
	@Override
	public double getProteins() {
		
		return proteins;
	}

	/**
	 * Total carbs in the menu
	 */
	@Override
	public double getCarbs() {
		
		return carbs;
	}

	/**
	 * Total fats in the menu
	 */
	@Override
	public double getFat() {
		return fat;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Menu} class it must always return {@code false}:
	 * nutritional values are provided for the whole menu.
	 * 
	 * @return boolean 	indicator
	 */
	@Override
	public boolean per100g() {
		// nutritional values are provided for the whole menu.
		return false;
	}
}
