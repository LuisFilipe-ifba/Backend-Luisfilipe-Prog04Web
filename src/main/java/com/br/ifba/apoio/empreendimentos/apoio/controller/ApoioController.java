package com.br.ifba.apoio.empreendimentos.apoio.controller;

import com.br.ifba.apoio.empreendimentos.apoio.dto.ApoioRequestDTO;
import com.br.ifba.apoio.empreendimentos.apoio.dto.ApoioResponseDTO;
import com.br.ifba.apoio.empreendimentos.apoio.model.Apoio;
import com.br.ifba.apoio.empreendimentos.apoio.service.ApoioService;
import com.br.ifba.apoio.empreendimentos.infrastructure.mapper.ObjectMapperUtil;
import com.br.ifba.apoio.empreendimentos.usuario.model.Usuario;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apoios")
@RequiredArgsConstructor
public class ApoioController {

    private final ApoioService apoioService;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping("/projeto/{projetoId}")
    public ResponseEntity<?> apoiar(@PathVariable Long projetoId,
                                    @AuthenticationPrincipal Usuario usuarioLogado,
                                    @Valid @RequestBody ApoioRequestDTO dto) {
        try {
            Apoio criado = apoioService.criarApoio(usuarioLogado, projetoId, dto.getValor());
            return ResponseEntity.status(HttpStatus.CREATED).body(objectMapperUtil.map(criado, ApoioResponseDTO.class));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/projeto/{projetoId}")
    public ResponseEntity<List<ApoioResponseDTO>> listarPorProjeto(@PathVariable Long projetoId) {
        return ResponseEntity.ok(objectMapperUtil.mapAll(apoioService.listarPorProjeto(projetoId), ApoioResponseDTO.class));
    }

    // "meus apoios": exige login, sempre o usuário autenticado

    @GetMapping("/meus")
    public ResponseEntity<List<ApoioResponseDTO>> listarMeusApoios(@AuthenticationPrincipal Usuario usuarioLogado) {
        return ResponseEntity.ok(objectMapperUtil.mapAll(apoioService.listarPorUsuario(usuarioLogado.getId()), ApoioResponseDTO.class));
    }
}