package com.br.ifba.apoio.empreendimentos.sessao.service;

import com.br.ifba.apoio.empreendimentos.sessao.model.Sessao;
import com.br.ifba.apoio.empreendimentos.usuario.model.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public interface SessaoIService {

    public Sessao criarSessao(Usuario usuario, String token, LocalDateTime dataExpiracao);

    public boolean sessaoValida(String token);

    public List<Sessao> listarPorUsuario(Long usuarioId);

    public void revogarPorToken(String token);


}
