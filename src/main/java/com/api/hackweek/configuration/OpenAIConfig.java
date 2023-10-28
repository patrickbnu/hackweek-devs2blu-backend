package com.api.hackweek.configuration;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class OpenAIConfig {
    @Value("${chat-gpt.api-key}")
    private String apiKey;

    @Bean
    public OpenAiService openAiService() {
        return new OpenAiService(apiKey, Duration.ofSeconds(30));
    }

    @Bean
    public ChatCompletionRequest.ChatCompletionRequestBuilder completionRequest() {
        return ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .n(1);
    }
}
