package com.indianairlines.management.system.conflig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorConfig {

    @Bean
    public Executor getConfig() {
        return Executors.newFixedThreadPool(10);
    }

}
