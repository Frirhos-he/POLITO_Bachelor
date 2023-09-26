package transactions;

public class Offer {

	private String offerId;
	private Place place;
	private String productId;
	private Transaction transactionId;
	
	
	
	public Offer(String offerId, Place place, String productId) {
		this.offerId = offerId;
		this.place = place;
		this.productId = productId;
	}
	public String getOfferId() {
		return offerId;
	}
	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}
	public Place getPlace() {
		return place;
	}
	public void setPlace(Place place) {
		this.place = place;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Transaction getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Transaction transactionId) {
		this.transactionId = transactionId;
	}
	
}
