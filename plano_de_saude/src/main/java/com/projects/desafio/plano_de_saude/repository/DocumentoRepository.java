package com.projects.desafio.plano_de_saude.repository;

import com.projects.desafio.plano_de_saude.domain.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {

}
