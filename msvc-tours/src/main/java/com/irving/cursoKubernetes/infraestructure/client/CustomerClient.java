package com.irving.cursoKubernetes.infraestructure.client;

import com.irving.cursoKubernetes.api.models.responses.ApiResponseDto;
import com.irving.cursoKubernetes.api.models.responses.CustomerResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "mvc-users", url = "http://localhost:8081")
public interface CustomerClient {

    @GetMapping("/customer/{id}")
    ApiResponseDto<CustomerResponseDto> getCustomerById(@PathVariable("id") String id);

}
