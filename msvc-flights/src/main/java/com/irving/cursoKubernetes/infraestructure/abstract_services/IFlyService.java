package com.irving.cursoKubernetes.infraestructure.abstract_services;


import com.irving.cursoKubernetes.api.models.responses.ApiResponseDto;
import com.irving.cursoKubernetes.api.models.responses.FlyResponseDto;

import java.util.Set;

public interface IFlyService extends  CatalogoService<FlyResponseDto> {

	ApiResponseDto<Set<FlyResponseDto>> readByOriginDestiny(String origin, String destiny);

	ApiResponseDto<FlyResponseDto> readById(Long id);
}
