package com.br.ifba.apoio.empreendimentos.categoria.controller;

import com.br.ifba.apoio.empreendimentos.categoria.dto.CategoriaRequestDTO;
import com.br.ifba.apoio.empreendimentos.categoria.dto.CategoriaResponseDTO;
import com.br.ifba.apoio.empreendimentos.categoria.model.Categoria;
import com.br.ifba.apoio.empreendimentos.categoria.service.CategoriaService;
import com.br.ifba.apoio.empreendimentos.infrastructure.mapper.ObjectMapperUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> criar(@Valid @RequestBody CategoriaRequestDTO dto) {
        Categoria categoria = objectMapperUtil.map(dto, Categoria.class);
        Categoria criada = categoriaService.criarCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(objectMapperUtil.map(criada, CategoriaResponseDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listarTodas() {
        List<CategoriaResponseDTO> categorias = categoriaService.listarTodas().stream()
                .map(categoria -> objectMapperUtil.map(categoria, CategoriaResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Categoria categoria = categoriaService.buscarPorId(id);
            return ResponseEntity.ok(objectMapperUtil.map(categoria, CategoriaResponseDTO.class));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody CategoriaRequestDTO dto) {
        try {
            Categoria dadosAtualizados = objectMapperUtil.map(dto, Categoria.class);
            Categoria atualizada = categoriaService.atualizarCategoria(id, dadosAtualizados);
            return ResponseEntity.ok(objectMapperUtil.map(atualizada, CategoriaResponseDTO.class));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            categoriaService.deletarCategoria(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // ---- Conversões DTO <-> Entidade (feitas aqui no controller) ----




}
