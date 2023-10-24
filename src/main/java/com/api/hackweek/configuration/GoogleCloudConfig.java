package com.api.hackweek.configuration;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleCloudConfig {
    @Value("${google.api-key}")
    private String apiKey;

    private static final String PORTUGUESE_LANGUAGE = "pt";

    @Bean
    public Translate translate() {
        return TranslateOptions.newBuilder()
                .setApiKey(apiKey)
                .setTargetLanguage(PORTUGUESE_LANGUAGE)
                .build()
                .getService();
    }
}
