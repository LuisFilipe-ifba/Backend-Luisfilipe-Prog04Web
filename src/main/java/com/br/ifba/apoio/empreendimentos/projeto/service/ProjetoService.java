package com.br.ifba.apoio.empreendimentos.projeto.service;

import com.br.ifba.apoio.empreendimentos.projeto.model.Projeto;
import com.br.ifba.apoio.empreendimentos.projeto.model.StatusProjeto;
import com.br.ifba.apoio.empreendimentos.projeto.repository.ProjetoRepository;
import com.br.ifba.apoio.empreendimentos.usuario.model.Usuario;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjetoService {

    private final ProjetoRepository projetoRepository;


    public Projeto criarProjeto(Usuario criador, Projeto projeto) {
        projeto.setCriador(criador);
        projeto.setValorArrecadado(0.0);
        projeto.setStatus(StatusProjeto.EM_ANALISE);
        return projetoRepository.save(projeto);
    }

    public List<Projeto> listarTodos() {
        return projetoRepository.findAll();
    }

    public Projeto buscarPorId(Long id) {
        return projetoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado com id: " + id));
    }

    public List<Projeto> listarPorCriador(Long criadorId) {
        return projetoRepository.findByCriador_Id(criadorId);
    }

    public List<Projeto> listarPorStatus(StatusProjeto status) {
        return projetoRepository.findByStatus(status);
    }

    public Projeto atualizarProjeto(Long id, Projeto dadosAtualizados, Usuario usuarioAutenticado) {
        Projeto existente = buscarPorId(id);
        verificarPropriedade(existente, usuarioAutenticado);

        existente.setTitulo(dadosAtualizados.getTitulo());
        existente.setDescricao(dadosAtualizados.getDescricao());
        existente.setValorNecessario(dadosAtualizados.getValorNecessario());
        existente.setCategoria(dadosAtualizados.getCategoria());

        return projetoRepository.save(existente);
    }

    public void deletarProjeto(Long id, Usuario usuarioAutenticado) {
        Projeto projeto = buscarPorId(id);
        verificarPropriedade(projeto, usuarioAutenticado);
        projetoRepository.delete(projeto);
    }

    private void verificarPropriedade(Projeto projeto, Usuario usuarioAutenticado) {
        if (!projeto.getCriador().getId().equals(usuarioAutenticado.getId())) {
            throw new AccessDeniedException("Você não tem permissão para alterar este projeto.");
        }
    }
}