package es.codeurjc.loyalty.models;


import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = "t_loyalty_card_number_view")
@Data
public class LoyaltyView {

	@Id
	private Long id;

	private String loyaltyCardNumber;

}
