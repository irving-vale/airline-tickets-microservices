package com.irving.cursoKubernetes.infraestructure.abstract_services;


import com.irving.cursoKubernetes.api.models.request.FlyLessPriceRequestDto;
import com.irving.cursoKubernetes.api.models.request.FlySearchOriginDestinyDto;
import com.irving.cursoKubernetes.api.models.request.PageableRequestDto;
import com.irving.cursoKubernetes.api.models.request.PriceRangeRequestDto;
import com.irving.cursoKubernetes.api.models.responses.ApiResponseDto;
import com.irving.cursoKubernetes.api.models.responses.FlyResponseDto;

import java.util.Set;

public interface IFlyService extends  CatalogoService<FlyResponseDto,PriceRangeRequestDto, FlyLessPriceRequestDto,FlySearchOriginDestinyDto,PageableRequestDto> {

	ApiResponseDto<Set<FlyResponseDto>> readByOriginDestiny(FlySearchOriginDestinyDto flySearchOriginDestinyDto);
}
