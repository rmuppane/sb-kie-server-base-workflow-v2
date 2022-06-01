package com.rh.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessConfig {

    private ProcessHandler handler;

    public ProcessConfig(ProcessHandler handler) {
        this.handler = handler;
    }
}