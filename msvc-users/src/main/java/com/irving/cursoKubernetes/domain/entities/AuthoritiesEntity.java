package com.irving.cursoKubernetes.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "authorities")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthoritiesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false , name = "authority_name")
    private String authorityName;


}
