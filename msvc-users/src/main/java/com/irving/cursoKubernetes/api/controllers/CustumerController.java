package com.irving.cursoKubernetes.api.controllers;

import com.irving.cursoKubernetes.api.models.responses.ApiResponseDto;
import com.irving.cursoKubernetes.api.models.responses.CustomerResponseDto;
import com.irving.cursoKubernetes.infraestructure.services.CustumerServices;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
@Validated
public class CustumerController {

    private final CustumerServices custumerServices;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<CustomerResponseDto>> findCustomerById(
            @PathVariable("id") @NotBlank String id) {
        return ResponseEntity.ok(custumerServices.findCustomerById(id));
    }

//    @GetMapping("/findAll")
//    public ResponseEntity<ApiResponseDto<List<CustomerResponseDto>>> findAllCustomers() {
//        return ResponseEntity.ok(custumerServices.findAll());
//    }

}
