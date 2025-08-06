package com.projects.desafio.plano_de_saude.domain.entity;

import jakarta.persistence.*;

@Entity
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tipoDocumento;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "benficiario_id")
    private Beneficiario beneficiario;

}
