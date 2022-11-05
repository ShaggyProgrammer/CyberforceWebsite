package com.vitavehiculum.web.initializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitavehiculum.web.orm.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.IOException;

public abstract class Initializer {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected ObjectMapper mapper;

    protected static JsonNode config;

    @PostConstruct
    public void postConstruct(){
        try {
            config = mapper.readTree(new ClassPathResource("config.json").getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
