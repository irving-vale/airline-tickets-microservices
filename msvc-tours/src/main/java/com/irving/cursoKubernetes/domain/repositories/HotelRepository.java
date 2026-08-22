package com.irving.cursoKubernetes.domain.repositories;

import com.irving.cursoKubernetes.domain.entities.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<HotelEntity,Long> {
}
