package com.br.ifba.apoio.empreendimentos.usuario.service;

import com.br.ifba.apoio.empreendimentos.usuario.model.Usuario;
import com.br.ifba.apoio.empreendimentos.usuario.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UsuarioIService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Usuario criar(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuario.setAtivo(true);
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com id: " + id));
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com email: " + email));
    }

    @Override
    public Usuario atualizar(Long id, Usuario dadosAtualizados) {
        Usuario existente = buscarPorId(id);

        existente.setNome(dadosAtualizados.getNome());
        existente.setEmail(dadosAtualizados.getEmail());
        existente.setPerfil(dadosAtualizados.getPerfil());

        // Só re-hashea a senha se uma nova senha (não vazia) foi enviada.
        if (dadosAtualizados.getSenha() != null && !dadosAtualizados.getSenha().isBlank()) {
            existente.setSenha(passwordEncoder.encode(dadosAtualizados.getSenha()));
        }

        return usuarioRepository.save(existente);
    }

    @Override
    public void desativar(Long id) {
        Usuario usuario = buscarPorId(id);
        usuario.setAtivo(false);
        usuarioRepository.save(usuario);
    }

    @Override
    public void deletar(Long id) {
        Usuario usuario = buscarPorId(id);
        usuarioRepository.delete(usuario);
    }
}