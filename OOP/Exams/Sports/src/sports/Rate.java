package sports;

public class Rate {

	private User user;
	private Product product;
	private int numStars;
	private String comment;
	
	public Rate(User user, Product product, int numStars, String comment) {
		this.user = user;
		this.product = product;
		this.numStars = numStars;
		this.comment = comment;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getNumStars() {
		return numStars;
	}
	public void setNumStars(int numStars) {
		this.numStars = numStars;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
}
