package transactions;

public class Request {

	private String requestId;
	private Place place;
	private String productId;
	private Transaction transactionId;

	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public Place getPlace() {
		return place;
	}
	public Request(String requestId, Place place, String productId) {
		this.requestId = requestId;
		this.place = place;
		this.productId = productId;
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
	public void setTransactionId(Transaction element) {
		this.transactionId = element;
	}
	
	
	
}
