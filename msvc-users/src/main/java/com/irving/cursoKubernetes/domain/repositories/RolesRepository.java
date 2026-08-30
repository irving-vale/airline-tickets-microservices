package com.irving.cursoKubernetes.domain.repositories;

import com.irving.cursoKubernetes.domain.entities.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<RolesEntity,Long> {
}
