package com.projects.desafio.plano_de_saude.mapper;

import com.projects.desafio.plano_de_saude.domain.dto.BeneficiarioRequest;
import com.projects.desafio.plano_de_saude.domain.dto.BeneficiarioResponse;
import com.projects.desafio.plano_de_saude.domain.dto.DocumentoDTO;
import com.projects.desafio.plano_de_saude.domain.entity.Beneficiario;
import com.projects.desafio.plano_de_saude.domain.entity.Documento;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class BeneficiarioMapper {

    public Beneficiario toEntity(BeneficiarioRequest req) {
        Beneficiario b = new Beneficiario();
        b.setNome(req.nome());
        b.setTelefone(req.telefone());
        b.setDataNascimento(req.dataNascimento());
        List<Documento> documentos = req.documentos().stream().map(d -> {
            Documento doc = new Documento();
            doc.setTipoDocumento(d.tipoDocumento());
            doc.setDescricao(d.decricao());
            doc.setBeneficiario(b);
            return doc;
        }).toList();
        b.setDocumentos(documentos);
        return b;
    }

    public BeneficiarioResponse toResponse(Beneficiario b) {
        List<DocumentoDTO> docs = b.getDocumentos().stream().map(d -> new DocumentoDTO(d.getTipoDocumento(), d.getDescricao())).toList();
                return new BeneficiarioResponse(b.getId(), b.getNome() ,b.getTelefone(), b.getDataNascimento(), docs );
    }
}
