package de.telran.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@TestConfiguration
public class TestConfig {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
