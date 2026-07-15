package com.br.ifba.apoio.empreendimentos.perfil.service;

import com.br.ifba.apoio.empreendimentos.perfil.model.Perfil;
import com.br.ifba.apoio.empreendimentos.perfil.repository.PerfilRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface PerfilIService {

    public Perfil criar(Perfil perfil);

    public List<Perfil> listar();

    public Perfil buscarPorId(Long id);

    public Perfil atualizar(Long id, Perfil dadosAtualizados);

    public void deletar(Long id);
}
