package delivery;

public class Order {

	private String[] dishNames;
	private Integer orderId;
	private int[] quantities;
	private String customerName;
	private int deliveryTime;
	private int deliveryDistance;
	private Resturant resturant;
	private Boolean delivered = false; 
	
	public Order(int orderId, String[] dishNames, int[] quantities, String customerName, int deliveryTime, int deliveryDistance, Resturant resturant) {
		// TODO Auto-generated constructor stub
		this.dishNames = dishNames;
		this.quantities = quantities;
		this.customerName = customerName;
		this.deliveryDistance = deliveryDistance;
		this.deliveryTime = deliveryTime;
		this.orderId= orderId;
		this.resturant = resturant;
	}

	public String[] getDishNames() {
		return dishNames;
	}

	public void setDishNames(String[] dishNames) {
		this.dishNames = dishNames;
	}

	public int[] getQuantities() {
		return quantities;
	}

	public void setQuantities(int[] quantities) {
		this.quantities = quantities;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public int getDeliveryDistance() {
		return deliveryDistance;
	}

	public void setDeliveryDistance(int deliveryDistance) {
		this.deliveryDistance = deliveryDistance;
	}

	public Resturant getResturant() {
		return resturant;
	}

	public void setResturant(Resturant resturant) {
		this.resturant = resturant;
	}

	public Boolean getDelivered() {
		return delivered;
	}

	public void setDelivered(Boolean delivered) {
		this.delivered = delivered;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	

}
