package com.projects.desafio.plano_de_saude.service;

import com.projects.desafio.plano_de_saude.domain.dto.BeneficiarioRequest;
import com.projects.desafio.plano_de_saude.domain.dto.BeneficiarioResponse;
import com.projects.desafio.plano_de_saude.domain.dto.DocumentoDTO;
import com.projects.desafio.plano_de_saude.domain.entity.Beneficiario;
import com.projects.desafio.plano_de_saude.domain.entity.Documento;
import com.projects.desafio.plano_de_saude.mapper.BeneficiarioMapper;
import com.projects.desafio.plano_de_saude.repository.BeneficiarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiarioService {

    private final BeneficiarioRepository beneficiarioRepository;
    private final BeneficiarioMapper beneficiarioMapper;

    public BeneficiarioService(BeneficiarioRepository beneficiarioRepository, BeneficiarioMapper beneficiarioMapper) {
        this.beneficiarioRepository = beneficiarioRepository;
        this.beneficiarioMapper = beneficiarioMapper;
    }

    public BeneficiarioResponse salvar(BeneficiarioRequest request){
        Beneficiario b = beneficiarioMapper.toEntity(request);
        return beneficiarioMapper.toResponse(beneficiarioRepository.save(b));
    }

    public List<BeneficiarioResponse> listarTodos (){
        return beneficiarioRepository.findAll().stream().map(beneficiarioMapper::toResponse).toList();
    }

    public List<DocumentoDTO> listarDocumentos(Long id){
        Beneficiario b = beneficiarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Beneficiário não encontrado"));
        return b.getDocumentos().stream().map(d -> new DocumentoDTO(d.getTipoDocumento(), d.getDescricao())).toList();
    }

    public void remover (Long id) {
        if (!beneficiarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Id do beneficiário não encontrado");
        }
        beneficiarioRepository.deleteById(id);
    }

    public BeneficiarioResponse atualizar(Long id, BeneficiarioRequest request) {
        Beneficiario b = beneficiarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Beneficiário não encontrado"));
        b.setNome(request.nome());
        b.setDataNascimento(request.dataNascimento());
        b.getDocumentos().clear();
        for (DocumentoDTO doc : request.documentos()) {
            Documento d = new Documento();
            d.setTipoDocumento(doc.tipoDocumento());
            d.setDescricao(doc.decricao());
            d.setBeneficiario(b);
            b.getDocumentos().add(d);
        }
        return beneficiarioMapper.toResponse(beneficiarioRepository.save(b));
    }
}
