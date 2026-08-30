package com.irving.cursoKubernetes.infraestructure.client;

import com.irving.cursoKubernetes.api.models.responses.ApiResponseDto;
import com.irving.cursoKubernetes.api.models.responses.CustomerResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MSVC-USERS")
public interface CustomerClient {

    @GetMapping("/api/v1/customer/{id}")
    ApiResponseDto<CustomerResponseDto> getCustomerById(@PathVariable("id") String id);

}
