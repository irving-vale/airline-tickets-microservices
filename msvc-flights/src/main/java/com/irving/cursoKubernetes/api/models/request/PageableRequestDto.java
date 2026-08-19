package com.irving.cursoKubernetes.api.models.request;

import com.irving.cursoKubernetes.utils.SortType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Data
@AllArgsConstructor
@SuperBuilder
public class PageableRequestDto {
    @NotNull( message = "The page never been null" )
    @Min(value = 1, message = "The page must be greater than 0")
    private Integer page =0;

    @NotNull( message = "The size never been null" )
    @Min(value = 1, message = "The size must be greater than 0")
    private Integer size =10;


    private SortType sortType = SortType.NONE;
}
