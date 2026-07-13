package com.br.ifba.apoio.empreendimentos.sessao.controller;

import com.br.ifba.apoio.empreendimentos.infrastructure.mapper.ObjectMapperUtil;
import com.br.ifba.apoio.empreendimentos.sessao.DTO.SessaoResponseDTO;
import com.br.ifba.apoio.empreendimentos.sessao.service.SessaoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessoes")
@RequiredArgsConstructor
public class SessaoController {

    private final SessaoService sessaoService;
    private final ObjectMapperUtil objectMapperUtil;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<SessaoResponseDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(objectMapperUtil.mapAll(sessaoService.listarPorUsuario(usuarioId), SessaoResponseDTO.class));
    }


    @PatchMapping("/{id}/revogar")
    public ResponseEntity<?> revogar(@PathVariable Long id) {
        try {
            sessaoService.revogarSessao(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}