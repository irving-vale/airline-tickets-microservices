package com.irving.cursoKubernetes.infraestructure.abstract_services;


import com.irving.cursoKubernetes.api.models.request.TourRequestDto;
import com.irving.cursoKubernetes.api.models.responses.ApiResponseDto;
import com.irving.cursoKubernetes.api.models.responses.TourResponseDto;

import java.util.UUID;

public interface ITourService extends CrudService<TourRequestDto, TourResponseDto, Long> {



    ApiResponseDto<TourResponseDto> addTicket(Long flyId, Long tourId);

    ApiResponseDto<TourResponseDto> removeTicket(UUID ticketId, Long tourId);

    ApiResponseDto<TourResponseDto> addReservation(UUID reservationId,Long tourId);

    ApiResponseDto<TourResponseDto> removeReservation(UUID reservationId,Long tourId);
}
