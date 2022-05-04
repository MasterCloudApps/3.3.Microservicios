package es.codeurjc.shop.apigateway.proxies;

public class Customer {

	private long id;
	private String name;
	private double credit;

	public Customer() {
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

	public double getCredit() {
		return credit;
	}

	public void setCredit(final double credit) {
		this.credit = credit;
	}
}