package com.irving.cursoKubernetes.api.controllers;

import com.irving.cursoKubernetes.api.models.responses.ApiResponseDto;
import com.irving.cursoKubernetes.api.models.responses.CustomerResponseDto;
import com.irving.cursoKubernetes.infraestructure.services.CustumerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustumerController {

    private final CustumerServices custumerServices;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<CustomerResponseDto>> findCustomerById(@PathVariable("id") String id) {
        return ResponseEntity.ok(custumerServices.findCustomerById(id));
    }

}
