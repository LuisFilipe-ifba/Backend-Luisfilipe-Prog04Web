package com.br.ifba.apoio.empreendimentos.projeto.service;

import com.br.ifba.apoio.empreendimentos.projeto.model.Projeto;
import com.br.ifba.apoio.empreendimentos.usuario.model.Usuario;

import java.util.List;

public interface ProjetoIService {

    public Projeto criar(String criadorEmail, Projeto projeto);

    public List<Projeto> listarTodos();

    public Projeto buscarPorId(Long id);

    public Usuario atualizar(Long id, Usuario usuarioAtualizado);

    public List<Projeto> listarPorStatus(String status);

    public List<Projeto> listarPorCriador(String criadorEmail);

    public Projeto atualizarProjeto(Long id, Projeto dadosAtualizados);

    public void deletarProjeto(Long id);
}
