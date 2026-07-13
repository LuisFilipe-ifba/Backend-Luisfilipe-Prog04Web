package com.br.ifba.apoio.empreendimentos.projeto.controller;

import com.br.ifba.apoio.empreendimentos.categoria.model.Categoria;
import com.br.ifba.apoio.empreendimentos.categoria.repository.CategoriaRepository;
import com.br.ifba.apoio.empreendimentos.infrastructure.mapper.ObjectMapperUtil;
import com.br.ifba.apoio.empreendimentos.projeto.dto.ProjetoRequestDTO;
import com.br.ifba.apoio.empreendimentos.projeto.dto.ProjetoResponseDTO;
import com.br.ifba.apoio.empreendimentos.projeto.model.Projeto;
import com.br.ifba.apoio.empreendimentos.projeto.service.ProjetoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projetos")
@RequiredArgsConstructor
public class ProjetoController {

    private final ProjetoService projetoService;
    private final CategoriaRepository categoriaRepository;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping("/criador/{criadorId}")
    public ResponseEntity<?> criar(@PathVariable Long criadorId, @Valid @RequestBody ProjetoRequestDTO dto) {
        try {
            Projeto projeto = objectMapperUtil.map(dto, Projeto.class);
            Projeto criado = projetoService.criarProjeto(criadorId, projeto);
            return ResponseEntity.status(HttpStatus.CREATED).body(objectMapperUtil.map(criado, ProjetoResponseDTO.class));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(path = "/listarTodos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProjetoResponseDTO>> listarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(objectMapperUtil.mapAll(this.projetoService.listarTodos(), ProjetoResponseDTO.class));
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
    public ResponseEntity<List<ProjetoResponseDTO>> listarPorStatus(@PathVariable String status) {
        return ResponseEntity.ok(objectMapperUtil.mapAll(projetoService.listarPorStatus(status), ProjetoResponseDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody ProjetoRequestDTO dto) {
        try {
            Projeto dadosAtualizados = objectMapperUtil.map(dto, Projeto.class);
            Projeto atualizado = projetoService.atualizarProjeto(id, dadosAtualizados);
            return ResponseEntity.ok(objectMapperUtil.map(atualizado, ProjetoResponseDTO.class));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/apoio")
    public ResponseEntity<?> registrarApoio(@PathVariable Long id, @RequestBody Map<String, Double> body) {
        Double valor = body.get("valor");
        if (valor == null || valor <= 0) {
            return ResponseEntity.badRequest().body("O campo 'valor' deve ser maior que zero.");
        }
        try {
            Projeto atualizado = projetoService.registrarApoioFinanceiro(id, valor);
            return ResponseEntity.ok(objectMapperUtil.map(atualizado, ProjetoResponseDTO.class));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            projetoService.deletarProjeto(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
/*
    private Projeto toEntity(ProjetoRequestDTO dto) {
        List<Categoria> categorias = categoriaRepository.findAllById(dto.getCategoriaIds());

        Projeto projeto = new Projeto();
        projeto.setTitulo(dto.getTitulo());
        projeto.setDescricao(dto.getDescricao());
        projeto.setValorNecessario(dto.getValorNecessario());
        projeto.setCategorias(categorias);
        return projeto;
    }

    private ProjetoResponseDTO toResponseDTO(Projeto projeto) {
        List<String> nomesCategorias = projeto.getCategorias().stream()
                .map(Categoria::getNome)
                .collect(Collectors.toList());

        return new ProjetoResponseDTO(
                projeto.getTitulo(),
                projeto.getDescricao(),
                projeto.getValorNecessario(),
                projeto.getValorArrecadado(),
                projeto.getStatus(),
                projeto.getDataCriacao(),
                projeto.getCriador().getNome(),
                nomesCategorias
        );
    }
}*/