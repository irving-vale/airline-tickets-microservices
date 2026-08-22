package com.irving.cursoKubernetes.infraestructure.helper;

import com.irving.cursoKubernetes.api.models.responses.CustomerResponseDto;
import com.irving.cursoKubernetes.domain.entities.CustomerEntity;
import com.irving.cursoKubernetes.domain.repositories.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
