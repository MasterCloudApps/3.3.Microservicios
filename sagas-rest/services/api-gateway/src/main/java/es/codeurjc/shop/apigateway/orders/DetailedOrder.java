package es.codeurjc.shop.apigateway.orders;

import es.codeurjc.shop.apigateway.proxies.Customer;
import es.codeurjc.shop.apigateway.proxies.Order;
import es.codeurjc.shop.apigateway.proxies.Product;

public class DetailedOrder {

	private long id;
	private Customer customer;
	private Product product;
	private double totalCost;	
	private int productQuantity;

	public DetailedOrder(Order order, Product product, Customer customer) {
		super();
		this.id = order.getId();
		this.customer = customer;
		this.product = product;
		this.totalCost = order.getTotalCost();
		this.productQuantity = order.getProductQuantity();
	}
	
	public DetailedOrder(long id, Customer customer, Product product, double totalCost, int productQuantity) {
		super();
		this.id = id;
		this.customer = customer;
		this.product = product;
		this.totalCost = totalCost;
		this.productQuantity = productQuantity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
}
