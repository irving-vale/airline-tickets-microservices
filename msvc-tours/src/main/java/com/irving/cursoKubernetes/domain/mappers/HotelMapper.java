package com.irving.cursoKubernetes.domain.mappers;

import com.irving.cursoKubernetes.api.models.responses.HotelResponseDto;
import com.irving.cursoKubernetes.domain.entities.HotelEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HotelMapper {

	HotelResponseDto toDto(HotelEntity hotel);
}
