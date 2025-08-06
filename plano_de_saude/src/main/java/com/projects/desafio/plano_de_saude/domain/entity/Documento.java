package com.projects.desafio.plano_de_saude.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Documento extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoDocumento;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "benficiario_id")
    private Beneficiario beneficiario;

}
