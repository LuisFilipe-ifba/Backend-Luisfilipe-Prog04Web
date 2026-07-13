package com.br.ifba.apoio.empreendimentos.sessao.service;

import com.br.ifba.apoio.empreendimentos.sessao.model.Sessao;
import com.br.ifba.apoio.empreendimentos.sessao.repository.SessaoRepository;
import com.br.ifba.apoio.empreendimentos.usuario.model.Usuario;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessaoService {

    private final SessaoRepository sessaoRepository;

    public Sessao criarSessao(Usuario usuario, String token, LocalDateTime dataExpiracao) {
        Sessao sessao = new Sessao();
        sessao.setUsuario(usuario);
        sessao.setToken(token);
        sessao.setDataExpiracao(dataExpiracao);
        sessao.setAtiva(true);
        return sessaoRepository.save(sessao);
    }

    public List<Sessao> listarPorUsuario(Long usuarioId) {
        return sessaoRepository.findByUsuario_IdAndAtivaTrue(usuarioId);
    }

    public Sessao buscarPorId(Long id) {
        return sessaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sessão não encontrada com id: " + id));
    }


    public boolean sessaoValida(String token) {
        return sessaoRepository.findByTokenAndAtivaTrue(token)
                .filter(sessao -> sessao.getDataExpiracao().isAfter(LocalDateTime.now()))
                .isPresent();
    }

    public void revogarSessao(Long id) {
        Sessao sessao = buscarPorId(id);
        sessao.setAtiva(false);
        sessaoRepository.save(sessao);
    }
}