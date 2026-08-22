package com.irving.cursoKubernetes.infraestructure.services;

import com.irving.cursoKubernetes.api.models.responses.ApiResponseDto;
import com.irving.cursoKubernetes.api.models.responses.CustomerResponseDto;
import com.irving.cursoKubernetes.domain.entities.CustomerEntity;
import com.irving.cursoKubernetes.domain.mappers.CustomerMapper;
import com.irving.cursoKubernetes.infraestructure.helper.CustumerLoader;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@AllArgsConstructor
public class CustumerServices {

    private final CustumerLoader customerLoader;
    private final CustomerMapper customerMapper;

    public ApiResponseDto<CustomerResponseDto> findCustomerById(String id) {

       CustomerEntity customer = customerLoader.findCustumerEntityById(id);

       CustomerResponseDto customerResponseDto = customerMapper.toCustomerResponseDto(customer);
        return ApiResponseDto.<CustomerResponseDto>builder()
                .status("success")
                .statusCode(200)
                .message("Customer found successfully")
                .data(customerResponseDto)
                .build();

    }
}
