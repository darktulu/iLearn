package com.simu.ilearn.server.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MyModelMapper extends ModelMapper {
    private ModelMapper mapper;

    @PostConstruct
    public void getObject() {
        if (mapper == null) {
            mapper = new ModelMapper();
        }
    }

    public ModelMapper mapper() {
        return mapper;
    }

    @Override
    public <D> D map(Object source, Class<D> destinationType) {
        return mapper.map(source, destinationType);
    }
}
