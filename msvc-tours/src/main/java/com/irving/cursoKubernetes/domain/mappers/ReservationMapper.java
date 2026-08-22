package com.irving.cursoKubernetes.domain.mappers;

import com.irving.cursoKubernetes.api.models.responses.ReservationResponseDto;
import com.irving.cursoKubernetes.domain.entities.ReservationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" ,uses = {HotelMapper.class})
public interface ReservationMapper {
	ReservationResponseDto toReservationResponseDto(ReservationEntity reservation);
	ReservationEntity toReservationEntity(ReservationResponseDto reservationResponseDto);
}
