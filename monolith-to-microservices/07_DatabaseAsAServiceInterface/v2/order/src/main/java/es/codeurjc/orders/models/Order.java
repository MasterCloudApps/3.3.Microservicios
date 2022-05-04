package es.codeurjc.orders.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "T_ORDER")
@Data
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name = "DATEORDER")
	private String dateOrder;

	@Column(nullable = false, name = "PURCHASER")
	private String purchaser;

	@Column(nullable = false, name = "ADDRESS")
	private String address;

	@Column(nullable = false, name = "TOTALPRICE")
	private String totalPrice;

}
