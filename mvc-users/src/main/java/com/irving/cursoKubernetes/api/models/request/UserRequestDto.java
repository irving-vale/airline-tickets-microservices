package com.irving.cursoKubernetes.api.models.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRequestDto {

    private String email;
    private String pwd;
    private Boolean enabled = true;
    private Long role;
}
