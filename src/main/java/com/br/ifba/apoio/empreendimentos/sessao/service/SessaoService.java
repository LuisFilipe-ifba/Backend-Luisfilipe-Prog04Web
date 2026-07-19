package com.br.ifba.apoio.empreendimentos.sessao.service;

import com.br.ifba.apoio.empreendimentos.sessao.model.Sessao;
import com.br.ifba.apoio.empreendimentos.sessao.repository.SessaoRepository;
import com.br.ifba.apoio.empreendimentos.usuario.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessaoService implements SessaoIService {

    private final SessaoRepository sessaoRepository;

    /**
     * Usado pelo AuthController no login, depois que o JwtService já gerou
     * o token. Aqui só persistimos o registro.
     */

    @Override
    public Sessao criarSessao(Usuario usuario, String token, LocalDateTime dataExpiracao) {
        Sessao sessao = new Sessao();
        sessao.setUsuario(usuario);
        sessao.setToken(token);
        sessao.setDataExpiracao(dataExpiracao);
        sessao.setAtiva(true);
        return sessaoRepository.save(sessao);
    }

    @Override
    public List<Sessao> listarPorUsuario(Long usuarioId) {
        return sessaoRepository.findByUsuario_IdAndAtivaTrue(usuarioId);
    }

    /**
     * Usado pelo JwtAuthenticationFilter para checar se um token ainda é
     * válido, além da validade natural de expiração do JWT.
     */

    @Override
    public boolean sessaoValida(String token) {
        return sessaoRepository.findByTokenAndAtivaTrue(token)
                .filter(sessao -> sessao.getDataExpiracao().isAfter(LocalDateTime.now()))
                .isPresent();
    }

    @Override
    public void revogarPorToken(String token) {
        sessaoRepository.findByToken(token)
                .ifPresent(sessao -> {
                    sessao.setAtiva(false);
                    sessaoRepository.save(sessao);
                });
    }
}