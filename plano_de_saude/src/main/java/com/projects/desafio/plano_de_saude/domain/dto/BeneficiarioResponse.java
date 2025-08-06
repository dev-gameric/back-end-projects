package com.projects.desafio.plano_de_saude.domain.dto;

import java.time.LocalDate;
import java.util.List;

public record BeneficiarioResponse(Long id, String nome, String telefone, LocalDate dataNascimento, List<DocumentoDTO> documentos) {
}
