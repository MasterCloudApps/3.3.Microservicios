package es.codeurjc.shop.apigateway.proxies;

public class Order {

	private long id;
	private long customerId;
	private double totalCost;
	private long productId;
	private int productQuantity;

	public Order() {
	}

	public Order(final long customerId, final double totalCost, final long productId, final int productQuantity) {
		this.customerId = customerId;
		this.totalCost = totalCost;
		this.productId = productId;
		this.productQuantity = productQuantity;
	}

	public long getId() {
		return id;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(final long customerId) {
		this.customerId = customerId;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(final double totalCost) {
		this.totalCost = totalCost;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(final long productId) {
		this.productId = productId;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(final int productQuantity) {
		this.productQuantity = productQuantity;
	}
}