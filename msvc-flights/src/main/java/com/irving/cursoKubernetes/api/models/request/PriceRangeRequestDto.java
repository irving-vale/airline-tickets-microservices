package com.irving.cursoKubernetes.api.models.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
@NoArgsConstructor
@Data
@AllArgsConstructor
@SuperBuilder
public class PriceRangeRequestDto extends PageableRequestDto {
    @NotNull( message = "The min price never been null" )
    @PositiveOrZero( message = "The min price must be positive or zero" )
    private BigDecimal min;
    @NotNull( message = "The max price never been null" )
    @PositiveOrZero( message = "The max price must be positive or zero" )
    private BigDecimal max;
}
