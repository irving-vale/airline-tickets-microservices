package com.irving.cursoKubernetes.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity(name = "customer")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class CustomerEntity implements Serializable {
	@Id
	private String dni;
	@Column(length = 50)
	private String fullName;
	@Column(length = 20)
	private String creditCard;
	@Column(length = 12)
	private String phoneNumber;
	private Integer totalFlights;
	private Integer totalLodgings;
	private Integer totalTours;




}
