package com.br.ifba.apoio.empreendimentos.infrastructure.mapper;


import com.br.ifba.apoio.empreendimentos.perfil.model.Perfil;
import com.br.ifba.apoio.empreendimentos.perfil.repository.PerfilRepository;
import com.br.ifba.apoio.empreendimentos.usuario.dto.UsuarioRequestDTO;
import com.br.ifba.apoio.empreendimentos.usuario.dto.UsuarioResponseDTO;
import com.br.ifba.apoio.empreendimentos.usuario.model.Usuario;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

/*
import com.br.ifba.apoio.empreendimentos.projeto.dto.ProjetoRequestDTO;
import com.br.ifba.apoio.empreendimentos.projeto.dto.ProjetoResponseDTO;
import com.br.ifba.apoio.empreendimentos.projeto.model.Projeto;
import com.br.ifba.apoio.empreendimentos.categoria.model.Categoria;
import com.br.ifba.apoio.empreendimentos.categoria.repository.CategoriaRepository;
*/

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ObjectMapperUtil {

    private final ModelMapper modelMapper = new ModelMapper();
    //private final CategoriaRepository categoriaRepository;
    private final PerfilRepository perfilRepository;

    @jakarta.annotation.PostConstruct
    private void configurar() {
        modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        //configurarMapeamentoProjeto();
        configurarMapeamentoUsuario();
    }


    /**
     * Mapeamentos que o ModelMapper não consegue resolver sozinho por nome/tipo:
     * - categoriaId (Long) -> categoria (Categoria): precisa consultar o banco.
     * - categoria (Categoria) -> categoria (String): mesmo nome, tipo diferente.
     *//*
    private void configurarMapeamentoProjeto() {
        Converter<Long, Categoria> idParaCategoria = ctx ->
                ctx.getSource() == null
                        ? null
                        : categoriaRepository.findById(ctx.getSource())
                          .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com id: " + ctx.getSource()));

        modelMapper.typeMap(ProjetoRequestDTO.class, Projeto.class)
                .addMappings(mapper -> mapper.using(idParaCategoria)
                        .map(ProjetoRequestDTO::getCategoriaId, Projeto::setCategoria));

        Converter<Categoria, String> categoriaParaNome = ctx ->
                ctx.getSource() == null ? null : ctx.getSource().getNome();

        modelMapper.typeMap(Projeto.class, ProjetoResponseDTO.class)
                .addMappings(mapper -> mapper.using(categoriaParaNome)
                        .map(Projeto::getCategoria, ProjetoResponseDTO::setCategoria));
    }
    */
    /**
     * Mapeamentos específicos de Usuario que o ModelMapper não resolve sozinho:
     * - perfilId (Long) -> perfil (Perfil): precisa consultar o banco.
     * - perfil (Perfil) -> perfil (String): mesmo nome, tipo diferente (extrai a descrição).
     */
    private void configurarMapeamentoUsuario() {
        Converter<Long, Perfil> idParaPerfil = ctx ->
                ctx.getSource() == null
                        ? null
                        : perfilRepository.findById(ctx.getSource())
                          .orElseThrow(() -> new EntityNotFoundException("Perfil não encontrado com id: " + ctx.getSource()));

        modelMapper.typeMap(UsuarioRequestDTO.class, Usuario.class)
                .addMappings(mapper -> mapper.using(idParaPerfil)
                        .map(UsuarioRequestDTO::getPerfilId, Usuario::setPerfil));

        Converter<Perfil, String> perfilParaDescricao = ctx ->
                ctx.getSource() == null ? null : ctx.getSource().getDescricao();

        modelMapper.typeMap(Usuario.class, UsuarioResponseDTO.class)
                .addMappings(mapper -> mapper.using(perfilParaDescricao)
                        .map(Usuario::getPerfil, UsuarioResponseDTO::setPerfil));
    }

    public <Input, Output> Output map(final Input object, final Class<Output> clazz) {
        return modelMapper.map(object, clazz);
    }

    public <Input, Output> List<Output> mapAll(List<Input> lista, Class<Output> clazz) {
        return lista.stream()
                .map(obj -> modelMapper.map(obj, clazz))
                .collect(Collectors.toList());
    }
}