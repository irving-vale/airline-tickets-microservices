package com.irving.cursoKubernetes.domain.repositories;

import com.irving.cursoKubernetes.domain.entities.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity,String> {
    Page<CustomerEntity> findAll(Pageable pageable);
}
