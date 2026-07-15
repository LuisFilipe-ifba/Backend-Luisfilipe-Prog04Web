package com.br.ifba.apoio.empreendimentos.usuario.controller;

import com.br.ifba.apoio.empreendimentos.infrastructure.mapper.ObjectMapperUtil;
import com.br.ifba.apoio.empreendimentos.usuario.DTO.UsuarioRequestDTO;
import com.br.ifba.apoio.empreendimentos.usuario.DTO.UsuarioResponseDTO;
import com.br.ifba.apoio.empreendimentos.usuario.model.Usuario;
import com.br.ifba.apoio.empreendimentos.usuario.service.UsuarioIService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioIService usuarioService;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(@Valid @RequestBody UsuarioRequestDTO dto) {
        Usuario usuario = objectMapperUtil.map(dto, Usuario.class);
        Usuario criado = usuarioService.criar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(objectMapperUtil.map(criado, UsuarioResponseDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(objectMapperUtil.mapAll(usuarioService.listar(), UsuarioResponseDTO.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.buscarPorId(id);
            return ResponseEntity.ok(objectMapperUtil.map(usuario, UsuarioResponseDTO.class));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO dto) {
        try {
            Usuario dadosAtualizados = objectMapperUtil.map(dto, Usuario.class);
            Usuario atualizado = usuarioService.atualizar(id, dadosAtualizados);
            return ResponseEntity.ok(objectMapperUtil.map(atualizado, UsuarioResponseDTO.class));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<?> desativar(@PathVariable Long id) {
        try {
            usuarioService.desativar(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            usuarioService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}