package com.br.ifba.apoio.empreendimentos.categoria.service;

import com.br.ifba.apoio.empreendimentos.categoria.model.Categoria;

import java.util.List;

public interface CategoriaIService {

    public Categoria criarCategoria(Categoria categoria);

    public List<Categoria> listarTodas();

    public Categoria buscarPorId(Long id);

    public Categoria atualizarCategoria(Long id, Categoria dadosAtualizados);

    public void deletarCategoria(Long id);
}
