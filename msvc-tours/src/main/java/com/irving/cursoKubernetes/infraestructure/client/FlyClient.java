package com.irving.cursoKubernetes.infraestructure.client;

import com.irving.cursoKubernetes.api.models.responses.ApiResponseDto;
import com.irving.cursoKubernetes.api.models.responses.FlyResponseDto;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "msvc-flights", url = "http://localhost:8084")
public interface FlyClient {

    ApiResponseDto<FlyResponseDto>
}
