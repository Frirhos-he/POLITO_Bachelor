package sports;
import java.util.*;
import java.util.stream.Collectors;

 
/**
 * Facade class for the research evaluation system
 *
 */
public class Sports {

	private Map<String,Activity> activitiesM = new TreeMap<>();
	private Map<String,Category> categoriesM = new TreeMap<>();
	private Map<String,Product> productsM = new TreeMap<>();
	private Map<String,User> users = new TreeMap<>();
    //R1
    /**
     * Define the activities types treated in the portal.
     * The method can be invoked multiple times to add different activities.
     * 
     * @param actvities names of the activities
     * @throws SportsException thrown if no activity is provided
     */
    public void defineActivities (String... activities) throws SportsException {
    	
    	if(activities.length == 0) throw new SportsException("idk bro");
    	
    	Activity element;
    	for(int i = 0; i< activities.length; ++i) {
    		element = new Activity(activities[i]);
    		activitiesM.put(activities[i], element);
    	}
    }

    /**
     * Retrieves the names of the defined activities.
     * 
     * @return activities names sorted alphabetically
     */
    public List<String> getActivities() {
        return activitiesM.keySet().stream().sorted().collect(Collectors.toList());
    }


    /**
     * Add a new category of sport products and the linked activities
     * 
     * @param name name of the new category
     * @param activities reference activities for the category
     * @throws SportsException thrown if any of the specified activity does not exist
     */
    public void addCategory(String name, String... linkedActivities) throws SportsException {
    	
    	List<Activity> activities = new ArrayList<>();
    	for(int i = 0; i< linkedActivities.length; ++i) {
    		if(!activitiesM.containsKey(linkedActivities[i])) throw new SportsException("idk");
    		activities.add(activitiesM.get(linkedActivities[i]));
    	}
    	

    	Category element = new Category(name,activities);
    	categoriesM.put(name, element);
    	
    	for(Activity tmp: activities) {
    		tmp.getCategories().add(element);
    	}
    }

    /**
     * Retrieves number of categories.
     * 
     * @return categories count
     */
    public int countCategories() {
        return categoriesM.size();
    }

    /**
     * Retrieves all the categories linked to a given activity.
     * 
     * @param activity the activity of interest
     * @return list of categories (sorted alphabetically)
     */
    public List<String> getCategoriesForActivity(String activity) {
    	
    	if(!activitiesM.containsKey(activity)) return new ArrayList<>();
    	
    	
        return activitiesM.get(activity).getCategories().stream().map(x->x.getName()).
         sorted(Comparator.naturalOrder()).collect(Collectors.toList());
    }

    //R2
    /**
     * Add a research group and the relative disciplines.
     * 
     * @param name name of the research group
     * @param disciplines list of disciplines
     * @throws SportsException thrown in case of duplicate name
     */
    public void addProduct(String name, String activityName, String categoryName) throws SportsException {
    	
    	if(this.productsM.containsKey(name)) throw new SportsException("idk");

    	Product element = new Product
    			(name, this.activitiesM.get(activityName),
    					this.categoriesM.get(categoryName));
    	
    	this.productsM.put(name, element);
    	this.categoriesM.get(categoryName).getProducts().add(element);
    	this.activitiesM.get(activityName).getProducts().add(element);
    	

    	
    	
    }

    /**
     * Retrieves the list of products for a given category.
     * The list is sorted alphabetically.
     * 
     * @param categoryName name of the category
     * @return list of products
     */
    public List<String> getProductsForCategory(String categoryName){
    	if(!this.categoriesM.containsKey(categoryName)) return new ArrayList<>();
        return this.categoriesM.get(categoryName)
        		.getProducts().stream().
        		map(x->x.getName()).
        		sorted(Comparator.naturalOrder()).collect(Collectors.toList());
    }

    /**
     * Retrieves the list of products for a given activity.
     * The list is sorted alphabetically.
     * 
     * @param activityName name of the activity
     * @return list of products
     */
    public List<String> getProductsForActivity(String activityName){
    	if(!this.activitiesM.containsKey(activityName)) return new ArrayList<>();
        return this.activitiesM.get(activityName)
        		.getProducts().stream().
        		map(x->x.getName()).
        		sorted(Comparator.naturalOrder()).collect(Collectors.toList());
    }

    /**
     * Retrieves the list of products for a given activity and a set of categories
     * The list is sorted alphabetically.
     * 
     * @param activityName name of the activity
     * @param categoryNames names of the categories
     * @return list of products
     */
    public List<String> getProducts(String activityName, String... categoryNames){
    	if(!activitiesM.containsKey(activityName)) return new ArrayList<>();
    	else {
    		return this.productsM.values().stream()
    		.filter(x->Arrays.asList(activityName).contains(x.getActivity().getName()))
    		.filter(x-> Arrays.asList(categoryNames).contains(x.getCategory().getName()))
    		.map(x->x.getName())
    		.sorted(Comparator.naturalOrder()).collect(Collectors.toList());
    	}
    }

    //    //R3
    /**
     * Add a new product rating
     * 
     * @param productName name of the product
     * @param userName name of the user submitting the rating
     * @param numStars score of the rating in stars
     * @param comment comment for the rating
     * @throws SportsException thrown numStars is not correct
     */
    public void addRating(String productName, String userName, int numStars, String comment) throws SportsException {
    	
    if(numStars<1 || numStars>5) throw new SportsException("idk");
    
    User user = new User(userName);
    this.users.put(userName, user);
   
    Rate rated = new Rate(user, this.productsM.get(productName),numStars,comment);
    user.getRatings().add(rated);
    if(!this.productsM.containsKey(productName)) throw new SportsException("idk");
    this.productsM.get(productName).getRatings().add(rated);
    
    }



    /**
     * Retrieves the ratings for the given product.
     * The ratings are sorted by descending number of stars.
     * 
     * @param productName name of the product
     * @return list of ratings sorted by stars
     */
    public List<String> getRatingsForProduct(String productName) {
        return this.productsM.get(productName).getRatings()
        		.stream().sorted(Comparator.comparing(Rate::getNumStars).reversed())
        		.map(x-> x.getNumStars() +" : "+ x.getComment())
        		.collect(Collectors.toList());
        		
    }


    //R4
    /**
     * Returns the average number of stars of the rating for the given product.
     * 
     * 
     * @param productName name of the product
     * @return average rating
     */
    public double getStarsOfProduct (String productName) {
        return this.productsM.get(productName).Avg();
    }

    /**
     * Computes the overall average stars of all ratings
     *  
     * @return average stars
     */
    public double averageStars() {
        return 	this.productsM.values().stream().map(x->x.getRatings()).
        		flatMap(x->x.stream())
            	.map(x->x.getNumStars()).mapToDouble(x->x).average().orElse(0);

    }
    //R5 Statistiche
    /**
     * For each activity return the average stars of the entered ratings.
     * 
     * Activity names are sorted alphabetically.
     * 
     * @return the map associating activity name to average stars
     */
    public SortedMap<String, Double> starsPerActivity() {
        return 	this.productsM.values().stream()
        		.filter(x->x.getRatings().size() != 0)
        		.map(x->x.getActivity())
        		.collect(
        				Collectors.toMap(
        						x->x.getName(),
        						x->x.avgA(),
        						(n1, n2) -> n1,
        						()-> new TreeMap<String, Double>
        						(Comparator.naturalOrder())
        						));
    }

    /**
     * For each average star rating returns a list of
     * the products that have such score.
     * 
     * Ratings are sorted in descending order.
     * 
     * @return the map linking the average stars to the list of products
     */
    public SortedMap<Double, List<String>> getProductsPerStars () {

        return this.productsM.values().stream()
        		.filter(x->x.Avg()!= 0).collect(
        		Collectors.toMap(x->x.getName(), x->x.Avg()))
        		.entrySet().stream().collect(Collectors.groupingBy(
        				x->x.getValue(),
        				()-> new TreeMap<Double, List<String>>(Comparator.reverseOrder()),
        				Collectors.mapping(x->x.getKey(), Collectors.toList())
        				));
      
    }

}