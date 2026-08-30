package com.irving.cursoKubernetes.domain.mappers;

import com.irving.cursoKubernetes.api.models.request.UserRequestDto;
import com.irving.cursoKubernetes.domain.entities.UsersEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UsersEntity toEntity(UserRequestDto userDto);
}
