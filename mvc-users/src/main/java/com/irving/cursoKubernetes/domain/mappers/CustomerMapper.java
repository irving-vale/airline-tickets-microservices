package com.irving.cursoKubernetes.domain.mappers;

import com.irving.cursoKubernetes.api.models.responses.CustomerResponseDto;
import com.irving.cursoKubernetes.domain.entities.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

 CustomerResponseDto toCustomerResponseDto (CustomerEntity customerEntity);
}
