package com.example.deepseek.config;

import com.alibaba.cloud.ai.deepseek.DeepSeekApi;
import com.alibaba.cloud.ai.deepseek.DeepSeekChatModel;
import com.alibaba.cloud.ai.deepseek.DeepSeekChatOptions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.ChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeepseekConfig {

    @Value("${spring.ai.alibaba.deepseek.api-key}")
    private String apiKey;

    @Value("${spring.ai.alibaba.deepseek.base-url:https://api.deepseek.com}")
    private String baseUrl;

    @Value("${spring.ai.alibaba.deepseek.model:deepseek-chat}")
    private String model;

    @Bean
    public DeepSeekApi deepSeekApi() {
        return new DeepSeekApi(baseUrl, apiKey);
    }

    @Bean
    public DeepSeekChatModel deepSeekChatModel(DeepSeekApi deepSeekApi) {
        DeepSeekChatOptions options = DeepSeekChatOptions.builder()
                .withModel(model)
                .withTemperature(0.6f)
                .withMaxTokens(2000)
                .build();
        return new DeepSeekChatModel(deepSeekApi, options);
    }

    @Bean
    public ChatClient deepSeekChatClient(DeepSeekChatModel deepSeekChatModel) {
        return ChatClient.builder(deepSeekChatModel)
                .defaultAdvisors(new ChatMemoryAdvisor(new InMemoryChatMemory()))
                .build();
    }
}
