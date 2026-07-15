package com.br.ifba.apoio.empreendimentos.perfil.controller;

import com.br.ifba.apoio.empreendimentos.infrastructure.mapper.ObjectMapperUtil;
import com.br.ifba.apoio.empreendimentos.perfil.DTO.PerfilRequestDTO;
import com.br.ifba.apoio.empreendimentos.perfil.DTO.PerfilResponseDTO;
import com.br.ifba.apoio.empreendimentos.perfil.model.Perfil;
import com.br.ifba.apoio.empreendimentos.perfil.service.PerfilIService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfis")
@RequiredArgsConstructor
public class PerfilController {

    private final PerfilIService perfilService;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping
    public ResponseEntity<PerfilResponseDTO> criar(@Valid @RequestBody PerfilRequestDTO dto) {
        Perfil perfil = objectMapperUtil.map(dto, Perfil.class);
        Perfil criado = perfilService.criar(perfil);
        return ResponseEntity.status(HttpStatus.CREATED).body(objectMapperUtil.map(criado, PerfilResponseDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<PerfilResponseDTO>> listar() {
        return ResponseEntity.ok(objectMapperUtil.mapAll(perfilService.listar(), PerfilResponseDTO.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Perfil perfil = perfilService.buscarPorId(id);
            return ResponseEntity.ok(objectMapperUtil.map(perfil, PerfilResponseDTO.class));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody PerfilRequestDTO dto) {
        try {
            Perfil dadosAtualizados = objectMapperUtil.map(dto, Perfil.class);
            Perfil atualizado = perfilService.atualizar(id, dadosAtualizados);
            return ResponseEntity.ok(objectMapperUtil.map(atualizado, PerfilResponseDTO.class));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            perfilService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}