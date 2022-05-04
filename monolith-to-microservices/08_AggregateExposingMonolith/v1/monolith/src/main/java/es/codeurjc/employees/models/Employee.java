package es.codeurjc.employees.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "T_EMPLOYEE")
@Data
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name = "NAME")
	private String name;

	@Column(nullable = false, name = "LAST_NAME")
	private String lastName;

	@Column(nullable = false, name = "BIRTH_DATE")
	private String birthDate;

	@Column(nullable = false, name = "DOCUMENT")
	private String document;

}
