package com.simu.ilearn.server.util;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializationConfig.Feature;

import javax.annotation.PostConstruct;

public class CustomObjectMapper extends ObjectMapper {
    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        SerializationConfig serialConfig = getSerializationConfig()
                .without(Feature.FAIL_ON_EMPTY_BEANS)
                .without(Feature.WRITE_DATES_AS_TIMESTAMPS);

        this.setSerializationConfig(serialConfig);
        this.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
    }
}
