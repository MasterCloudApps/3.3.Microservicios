package es.codeurjc.users.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "T_USER")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name = "ADDRESS")
	private String address;

	@Column(nullable = false, name = "PASSWORD")
	private String password;

	@Column(nullable = false, name = "LOYALTY_CARD_NUMBER")
	private String loyaltyCardNumber;

}
