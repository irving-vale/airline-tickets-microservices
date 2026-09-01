package com.irving.cursoKubernetes.infraestructure.abstract_services;


import com.irving.cursoKubernetes.api.models.responses.ApiResponseDto;

import javax.naming.ServiceUnavailableException;

public interface CrudService<RQ, RS, ID> {

    ApiResponseDto<RS>   create(RQ request) throws ServiceUnavailableException;

    ApiResponseDto<RS> read(ID id);

    ApiResponseDto<RS> update(ID id, RQ request) throws ServiceUnavailableException;

    ApiResponseDto<Void>delete(ID id);


}
