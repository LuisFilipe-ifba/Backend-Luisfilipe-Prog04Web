package com.br.ifba.apoio.empreendimentos.usuario.service;

import com.br.ifba.apoio.empreendimentos.usuario.model.Usuario;

import java.util.List;

public interface UsuarioIService {

    public Usuario criar(Usuario usuario);

    public List<Usuario> listar();

    public Usuario buscarPorId(Long id);

    public Usuario atualizar(Long id, Usuario usuarioAtualizado);

    public void deletar(Long id);

    public Usuario buscarPorEmail(String email);

    public void desativar(Long id);
}
