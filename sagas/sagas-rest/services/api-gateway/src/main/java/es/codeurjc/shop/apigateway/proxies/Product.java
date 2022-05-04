package es.codeurjc.shop.apigateway.proxies;

public class Product {

	private long id;
	private String name;
	private int stock;

	public Product() {
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(final int stock) {
		this.stock = stock;
	}
}