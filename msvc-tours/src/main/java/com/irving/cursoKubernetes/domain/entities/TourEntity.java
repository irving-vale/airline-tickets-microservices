package com.irving.cursoKubernetes.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity(name = "tour")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class TourEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(mappedBy = "tour",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true,
	        fetch = FetchType.LAZY
	)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<ReservationEntity> reservations;

	private String customer;

//	TODO: Manejar la adicion a columnas de ticket el id de tours

	@PrePersist
	@PreUpdate
	@PreRemove
	public void updateFk() {
		if (reservations != null) {
			reservations.forEach(reservation -> reservation.setTour(this));
		}
	}


	public void addReservation(ReservationEntity reservation) {
		if (Objects.isNull(this.reservations)) this.reservations = new HashSet<>();
		this.reservations.add(reservation);
		reservation.setTour(this);
	}

	public void removeReservation(UUID id) {
		if (reservations == null) return;
		reservations.removeIf(reservation -> {
			if (reservation.getId().equals(id)) {
				reservation.setTour(null);
				return true; // lo elimina de la colección
			}
			return false;
		});
	}





}


