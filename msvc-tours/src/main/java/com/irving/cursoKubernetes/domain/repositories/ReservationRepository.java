package com.irving.cursoKubernetes.domain.repositories;

import com.irving.cursoKubernetes.domain.entities.ReservationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReservationRepository extends CrudRepository<ReservationEntity, UUID> {
}
