package com.irving.cursoKubernetes.infraestructure.abstract_services;


import com.irving.cursoKubernetes.api.models.responses.ApiResponseDto;

import java.util.List;

public interface CatalogoService<RS,PR,LP,SO,PG> {

   ApiResponseDto<List<RS>>findAllPagination(PG pageable);

   ApiResponseDto<List<RS> >readLessPrice(LP lessPrice);

   ApiResponseDto<List<RS> > readBetweenPrice(PR priceRange);

    String FIELD_BY_SORT = "price";
}
