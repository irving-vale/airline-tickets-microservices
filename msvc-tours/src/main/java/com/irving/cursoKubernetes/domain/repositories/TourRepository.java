package com.irving.cursoKubernetes.domain.repositories;

import com.irving.cursoKubernetes.domain.entities.TourEntity;
import org.springframework.data.repository.CrudRepository;

public interface TourRepository extends CrudRepository<TourEntity, Long> {

}
