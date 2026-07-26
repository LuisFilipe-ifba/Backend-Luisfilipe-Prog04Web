package com.br.ifba.apoio.empreendimentos.projeto.controller;

import com.br.ifba.apoio.empreendimentos.infrastructure.mapper.ObjectMapperUtil;
import com.br.ifba.apoio.empreendimentos.projeto.dto.ProjetoRequestDTO;
import com.br.ifba.apoio.empreendimentos.projeto.dto.ProjetoResponseDTO;
import com.br.ifba.apoio.empreendimentos.projeto.model.Projeto;
import com.br.ifba.apoio.empreendimentos.projeto.model.StatusProjeto;
import com.br.ifba.apoio.empreendimentos.projeto.service.ProjetoService;
import com.br.ifba.apoio.empreendimentos.usuario.model.Usuario;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projetos")
@RequiredArgsConstructor
public class ProjetoController {

    private final ProjetoService projetoService;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping
    public ResponseEntity<?> criar(@AuthenticationPrincipal Usuario usuarioLogado,
                                   @Valid @RequestBody ProjetoRequestDTO dto) {
        Projeto projeto = objectMapperUtil.map(dto, Projeto.class);
        Projeto criado = projetoService.criarProjeto(usuarioLogado, projeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(objectMapperUtil.map(criado, ProjetoResponseDTO.class));
    }

    @GetMapping(path = "/listarTodos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProjetoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(objectMapperUtil.mapAll(projetoService.listarTodos(), ProjetoResponseDTO.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Projeto projeto = projetoService.buscarPorId(id);
            return ResponseEntity.ok(objectMapperUtil.map(projeto, ProjetoResponseDTO.class));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/criador/{criadorId}")
    public ResponseEntity<List<ProjetoResponseDTO>> listarPorCriador(@PathVariable Long criadorId) {
        return ResponseEntity.ok(objectMapperUtil.mapAll(projetoService.listarPorCriador(criadorId), ProjetoResponseDTO.class));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ProjetoResponseDTO>> listarPorStatus(@PathVariable StatusProjeto status) {
        return ResponseEntity.ok(objectMapperUtil.mapAll(projetoService.listarPorStatus(status), ProjetoResponseDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id,
                                       @AuthenticationPrincipal Usuario usuarioLogado,
                                       @Valid @RequestBody ProjetoRequestDTO dto) {
        try {
            Projeto dadosAtualizados = objectMapperUtil.map(dto, Projeto.class);
            Projeto atualizado = projetoService.atualizarProjeto(id, dadosAtualizados, usuarioLogado);
            return ResponseEntity.ok(objectMapperUtil.map(atualizado, ProjetoResponseDTO.class));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado) {
        try {
            projetoService.deletarProjeto(id, usuarioLogado);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}