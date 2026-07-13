package com.br.ifba.apoio.empreendimentos.perfil.service;

import com.br.ifba.apoio.empreendimentos.perfil.model.Perfil;
import com.br.ifba.apoio.empreendimentos.perfil.repository.PerfilRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerfilService {

    private final PerfilRepository perfilRepository;

    public Perfil criar(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    public List<Perfil> listar() {
        return perfilRepository.findAll();
    }

    public Perfil buscarPorId(Long id) {
        return perfilRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Perfil não encontrado com id: " + id));
    }

    public Perfil atualizar(Long id, Perfil dadosAtualizados) {
        Perfil existente = buscarPorId(id);

        existente.setDescricao(dadosAtualizados.getDescricao());
        existente.setPermissoes(dadosAtualizados.getPermissoes());

        return perfilRepository.save(existente);
    }

    public void deletar(Long id) {
        Perfil perfil = buscarPorId(id);
        perfilRepository.delete(perfil);
    }
}