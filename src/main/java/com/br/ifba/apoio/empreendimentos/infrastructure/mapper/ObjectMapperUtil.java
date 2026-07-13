package com.br.ifba.apoio.empreendimentos.infrastructure.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MatchingStrategy;
import org.modelmapper.convention.MatchingStrategies;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.config.Configuration;

public class ObjectMapperUtil {

    private static final ModelMapper MODEL_MAPPER;

    static {
        MODEL_MAPPER = new ModelMapper();
    }

    public <Input, Output> Output map(final Input object, final Class<Output> clazz){

        MODEL_MAPPER.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        Output c = MODEL_MAPPER.map(object, clazz);

        return c;
    }

    public <Input, Output> List<Output> mapAll(List<Input> lista, Class<Output> clazz){

        return lista.stream()
                .map(obj -> MODEL_MAPPER.map(obj, clazz))
                .collect(Collectors.toList());
    }



}
