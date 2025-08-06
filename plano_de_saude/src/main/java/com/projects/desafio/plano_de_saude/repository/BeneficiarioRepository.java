package com.projects.desafio.plano_de_saude.repository;

import com.projects.desafio.plano_de_saude.domain.entity.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long> {

}
