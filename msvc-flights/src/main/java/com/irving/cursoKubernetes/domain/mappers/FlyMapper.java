package com.irving.cursoKubernetes.domain.mappers;

import com.irving.cursoKubernetes.api.models.responses.FlyResponseDto;
import com.irving.cursoKubernetes.domain.entities.FlyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlyMapper {
	FlyResponseDto toDto(FlyEntity fly);
	FlyEntity toEntity(FlyResponseDto flyResponseDto);
}
