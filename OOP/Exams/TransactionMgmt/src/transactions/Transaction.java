package transactions;

public class Transaction {
	
	private String transactionId;
	private Carrier carrier;
	private Request request;
	private Offer offer;
	private Integer score = 0;
	private String productId;
	
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Carrier getCarrier() {
		return carrier;
	}
	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}
	public Request getRequest() {
		return request;
	}
	public Transaction(String transactionId, Carrier carrier, Request request, Offer offer, String productId) {
		this.transactionId = transactionId;
		this.carrier = carrier;
		this.request = request;
		this.offer = offer;
		this.productId = productId;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	public Offer getOffer() {
		return offer;
	}
	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	


}
