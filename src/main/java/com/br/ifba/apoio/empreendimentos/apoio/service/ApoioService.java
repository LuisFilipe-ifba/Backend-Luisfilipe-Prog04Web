package com.br.ifba.apoio.empreendimentos.apoio.service;

import com.br.ifba.apoio.empreendimentos.apoio.model.Apoio;
import com.br.ifba.apoio.empreendimentos.apoio.repository.ApoioRepository;
import com.br.ifba.apoio.empreendimentos.projeto.model.Projeto;
import com.br.ifba.apoio.empreendimentos.projeto.model.StatusProjeto;
import com.br.ifba.apoio.empreendimentos.projeto.repository.ProjetoRepository;
import com.br.ifba.apoio.empreendimentos.usuario.model.Usuario;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApoioService {

    private final ApoioRepository apoioRepository;
    private final ProjetoRepository projetoRepository;


    @Transactional
    public Apoio criarApoio(Usuario apoiador, Long projetoId, Double valor) {
        Projeto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado com id: " + projetoId));

        double novoValorArrecadado = projeto.getValorArrecadado() + valor;
        projeto.setValorArrecadado(novoValorArrecadado);

        if (novoValorArrecadado >= projeto.getValorNecessario()) {
            projeto.setStatus(StatusProjeto.FINANCIADO);
        }

        projetoRepository.save(projeto);

        Apoio apoio = new Apoio();
        apoio.setUsuario(apoiador);
        apoio.setProjeto(projeto);
        apoio.setValor(valor);

        return apoioRepository.save(apoio);
    }

    public List<Apoio> listarPorUsuario(Long usuarioId) {
        return apoioRepository.findByUsuario_Id(usuarioId);
    }

    public List<Apoio> listarPorProjeto(Long projetoId) {
        return apoioRepository.findByProjeto_Id(projetoId);
    }
}