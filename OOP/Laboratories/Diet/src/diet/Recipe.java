package diet;

import java.util.LinkedList;
import java.util.List;



/**
 * Represents a recipe of the diet.
 * 
 * A recipe consists of a a set of ingredients that are given amounts of raw materials.
 * The overall nutritional values of a recipe can be computed
 * on the basis of the ingredients' values and are expressed per 100g
 * 
 *
 */
public class Recipe implements NutritionalElement {
    
	private String name;
	private Food food;
	
	//private List<RawMaterial> ingredientElement = new ArrayList<>();
	//private List<Double> ingredientDouble = new ArrayList<>();
	
	private List<Ingredient> ingredientElement = new LinkedList<>();
	private static class Ingredient {  //to be more compact
		final NutritionalElement en;
		final double qty;
		Ingredient(NutritionalElement e, double q){
			this.en=e; this.qty=q;
		}
	}
	
	
	private double calories = 0 ;
	private double proteins = 0;
	private double carbs = 0;
	private double fat = 0;
	private double weight = 0;
	
	public Recipe(String name, Food food) {
		this.name = name;
		this.food = food;
		
	}
	
	/**
	 * Adds a given quantity of an ingredient to the recipe.
	 * The ingredient is a raw material.
	 * 
	 * @param material the name of the raw material to be used as ingredient
	 * @param quantity the amount in grams of the raw material to be used
	 * @return the same Recipe object, it allows method chaining.
	 */
	public Recipe addIngredient(String material, double quantity) {
		
		//Double doub = Double.valueOf(quantity);
		

		//ingredientElement.add((RawMaterial)Food.mapRawMaterial.get(material));
	    //ingredientDouble.add(doub);
		
		//MORE compact
		NutritionalElement en = food.getRawMaterial(material);

		Ingredient ing = new Ingredient(en,quantity);
		ingredientElement.add(ing);
		
		calories += en.getCalories(quantity);
		proteins += en.getProteins(quantity);
		carbs += en.getCarbs(quantity);
		fat += en.getFat(quantity);
		weight += quantity;
		
		return this;
	}

	@Override
	public String getName() {
		return name;
	}


	@Override
	public double getCalories() {
		
		return (calories/weight) *100;
	}

	@Override
	public double getProteins() {
		
		
		return (proteins/weight) *100;
	}

	@Override
	public double getCarbs() {
		
		return (carbs/weight)  *100;
	}

	@Override
	public double getFat() {
		
		return (fat/weight)    *100; //must be exspressed in 100 grams
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Recipe} class it must always return {@code true}:
	 * a recipe expresses nutritional values per 100g
	 * 
	 * @return boolean indicator
	 */
	@Override
	public boolean per100g() {
		return true;
	}
	
	
	/**
	 * Returns the ingredients composing the recipe.
	 * 
	 * A string that contains all the ingredients, one per per line, 
	 * using the following format:
	 * {@code "Material : ###.#"} where <i>Material</i> is the name of the 
	 * raw material and <i>###.#</i> is the relative quantity. 
	 * 
	 * Lines are all terminated with character {@code '\n'} and the ingredients 
	 * must appear in the same order they have been added to the recipe.
	 */
	@Override
	public String toString() {
		
		StringBuffer stringa = new StringBuffer();
		
		 for(int i = 0; i < ingredientElement.size(); ++i ) {

			 stringa.append(ingredientElement.get(i).en.getName() + " : " + String.format("%3.1f",ingredientElement.get(i).qty) + "\n");
		 }
				
				
		
		return stringa.toString();
	}
}
