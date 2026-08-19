package com.irving.cursoKubernetes.api.models.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class FlyLessPriceRequestDto extends PageableRequestDto {
    @NotNull( message = "The price never been null" )
    @Positive( message = "The price must be positive" )
    private BigDecimal price;
}
