package com.projects.desafio.plano_de_saude.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Beneficiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String telefone;
    private String dataNascimento;

    @OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<Documento> documentos = new ArrayList<>();

}
