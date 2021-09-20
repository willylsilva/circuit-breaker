package com.poc.willy.circuitbreakerpoc.adapter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties("app-config")
public class AppConfig {

    @Value("${app-config.dumb-localhost.url}")
    private String url;

    private Map<Integer, String> bankId;

    public String getUrl() {
        return url;
    }

    public Map<Integer, String> getBankId() {
        return bankId;
    }

    public void setBankId(Map<Integer, String> bankId) {
        this.bankId = bankId;
    }
}
