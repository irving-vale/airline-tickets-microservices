package com.irving.cursoKubernetes.infraestructure.helper;

import com.irving.cursoKubernetes.domain.entities.CustomerEntity;
import com.irving.cursoKubernetes.domain.repositories.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Transactional
@Component
@RequiredArgsConstructor
public class CustumerLoader {
    private final CustomerRepository customerRepository;

    public CustomerEntity findCustumerEntityById(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
    }


}
