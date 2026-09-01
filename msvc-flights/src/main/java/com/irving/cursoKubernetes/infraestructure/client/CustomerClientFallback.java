package com.irving.cursoKubernetes.infraestructure.client;

import com.irving.cursoKubernetes.api.models.responses.ApiResponseDto;
import com.irving.cursoKubernetes.api.models.responses.CustomerResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerClientFallback implements CustomerClient{
    @Override
    public ApiResponseDto<CustomerResponseDto> getCustomerById(String id) {
        CustomerResponseDto fallbackCustomer = CustomerResponseDto.builder()
                .dni(id)
                .fullName("Customer not found (Service Down)")
                .build();
        return ApiResponseDto.<CustomerResponseDto>builder()
                .status("degraded")
                .message("Respuesta devuelta desde Fallback por indisponibilidad de msvc-users")
                .statusCode(200)
                .data(fallbackCustomer)
                .build();
    }
}
