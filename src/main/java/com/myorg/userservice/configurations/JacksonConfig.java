package com.myorg.userservice.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.cas.jackson2.CasJackson2Module;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        
        // Register CAS Jackson module
        objectMapper.registerModule(new CasJackson2Module());
        
        return objectMapper;
    }
}
