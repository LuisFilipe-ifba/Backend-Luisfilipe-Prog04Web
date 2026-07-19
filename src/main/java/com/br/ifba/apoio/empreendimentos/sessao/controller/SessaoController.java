package com.br.ifba.apoio.empreendimentos.sessao.controller;

import com.br.ifba.apoio.empreendimentos.infrastructure.mapper.ObjectMapperUtil;
import com.br.ifba.apoio.empreendimentos.sessao.dto.SessaoResponseDTO;
import com.br.ifba.apoio.empreendimentos.sessao.service.SessaoIService;
import com.br.ifba.apoio.empreendimentos.sessao.service.SessaoService;
import com.br.ifba.apoio.empreendimentos.usuario.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/sessoes")
@RequiredArgsConstructor
public class SessaoController {

    private final SessaoIService sessaoService;
    private final ObjectMapperUtil objectMapperUtil;

    @GetMapping("/minhas")
    public ResponseEntity<List<SessaoResponseDTO>> listarMinhasSessoes(@AuthenticationPrincipal Usuario usuarioLogado) {
        List<SessaoResponseDTO> sessoes = objectMapperUtil.mapAll(
                sessaoService.listarPorUsuario(usuarioLogado.getId()), SessaoResponseDTO.class);
        return ResponseEntity.ok(sessoes);
    }
}