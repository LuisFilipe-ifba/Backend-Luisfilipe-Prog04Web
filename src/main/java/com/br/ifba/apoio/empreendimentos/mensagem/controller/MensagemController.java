package com.br.ifba.apoio.empreendimentos.mensagem.controller;

import com.br.ifba.apoio.empreendimentos.infrastructure.mapper.ObjectMapperUtil;
import com.br.ifba.apoio.empreendimentos.mensagem.dto.MensagemRequestDTO;
import com.br.ifba.apoio.empreendimentos.mensagem.dto.MensagemResponseDTO;
import com.br.ifba.apoio.empreendimentos.mensagem.model.Mensagem;
import com.br.ifba.apoio.empreendimentos.mensagem.service.MensagemService;
import com.br.ifba.apoio.empreendimentos.usuario.model.Usuario;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mensagens")
@RequiredArgsConstructor
public class MensagemController {

    private final MensagemService mensagemService;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping("/projeto/{projetoId}")
    public ResponseEntity<?> criar(@PathVariable Long projetoId,
                                   @AuthenticationPrincipal Usuario usuarioLogado,
                                   @Valid @RequestBody MensagemRequestDTO dto) {
        try {
            Mensagem criada = mensagemService.criarMensagem(usuarioLogado, projetoId, dto.getMensagem());
            return ResponseEntity.status(HttpStatus.CREATED).body(objectMapperUtil.map(criada, MensagemResponseDTO.class));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Público: ver as mensagens de um projeto é como ver comentários
     * de um post — não precisa estar logado, mesma lógica de
     * GET /api/projetos e GET /api/apoios/projeto/{id}.
     */
    @GetMapping("/projeto/{projetoId}")
    public ResponseEntity<List<MensagemResponseDTO>> listarPorProjeto(@PathVariable Long projetoId) {
        return ResponseEntity.ok(objectMapperUtil.mapAll(mensagemService.listarPorProjeto(projetoId), MensagemResponseDTO.class));
    }

    @GetMapping("/minhas")
    public ResponseEntity<List<MensagemResponseDTO>> listarMinhasMensagens(@AuthenticationPrincipal Usuario usuarioLogado) {
        return ResponseEntity.ok(objectMapperUtil.mapAll(mensagemService.listarPorUsuario(usuarioLogado.getId()), MensagemResponseDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado) {
        try {
            mensagemService.deletarMensagem(id, usuarioLogado);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}