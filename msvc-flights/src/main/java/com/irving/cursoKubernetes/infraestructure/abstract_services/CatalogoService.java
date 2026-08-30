package com.irving.cursoKubernetes.infraestructure.abstract_services;


import com.irving.cursoKubernetes.api.models.responses.ApiResponseDto;
import com.irving.cursoKubernetes.utils.SortType;

import java.math.BigDecimal;
import java.util.List;

public interface CatalogoService<R> {

   ApiResponseDto<List<R>>findAllPagination(Integer page, Integer size, SortType sortType);

   ApiResponseDto<List<R> >readLessPrice(Integer page, Integer size, SortType sortType, BigDecimal price);

   ApiResponseDto<List<R> > readBetweenPrice(BigDecimal min, BigDecimal max);

    String FIELD_BY_SORT = "price";
}
