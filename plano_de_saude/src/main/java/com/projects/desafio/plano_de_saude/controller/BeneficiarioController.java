package com.projects.desafio.plano_de_saude.controller;

import com.projects.desafio.plano_de_saude.domain.dto.BeneficiarioRequest;
import com.projects.desafio.plano_de_saude.domain.dto.BeneficiarioResponse;
import com.projects.desafio.plano_de_saude.domain.dto.DocumentoDTO;
import com.projects.desafio.plano_de_saude.domain.entity.Beneficiario;
import com.projects.desafio.plano_de_saude.service.BeneficiarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beneficiarios")
public class BeneficiarioController {

    private final BeneficiarioService beneficiarioService;

    public BeneficiarioController(BeneficiarioService beneficiarioService) {
        this.beneficiarioService = beneficiarioService;
    }

    @PostMapping
    public ResponseEntity<BeneficiarioResponse> criar(@RequestBody BeneficiarioRequest Request){
        return ResponseEntity.status(HttpStatus.CREATED).body(beneficiarioService.salvar(Request));
    }

    @GetMapping
    public List<BeneficiarioResponse> listarTodos(){
        return  beneficiarioService.listarTodos();
    }

    @GetMapping("/{id}/documentos")
    public List<DocumentoDTO> listarDocumentos(@PathVariable Long id){
        return beneficiarioService.listarDocumentos(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeneficiarioResponse> atualizar(@PathVariable Long id, @RequestBody BeneficiarioRequest Request){
        return ResponseEntity.ok(beneficiarioService.atualizar(id, Request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BeneficiarioResponse> remover(@PathVariable Long id){
        beneficiarioService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
