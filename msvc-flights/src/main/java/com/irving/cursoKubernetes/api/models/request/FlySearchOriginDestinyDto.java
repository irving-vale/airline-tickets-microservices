package com.irving.cursoKubernetes.api.models.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Data
@AllArgsConstructor
@SuperBuilder
public class FlySearchOriginDestinyDto extends PageableRequestDto {
    @NotBlank(message = "El origen no puede estar vacío")
    private String origin;

    @NotBlank(message = "El destino no puede estar vacío")
    private String destiny;
}
