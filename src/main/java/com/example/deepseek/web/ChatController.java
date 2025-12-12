package com.example.deepseek.web;

import com.example.deepseek.dto.ChatRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.api.AdvisorResponse;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @PostMapping
    public ResponseEntity<AdvisorResponse> chat(@RequestBody @Validated ChatRequest request) {
        PromptTemplate template = new PromptTemplate("请用简洁中文回答用户问题：{userInput}");
        Prompt prompt = template.create(Map.of("userInput", request.getPrompt()));
        Message response = chatClient.prompt(prompt).call().content();
        AdvisorResponse advisorResponse = new AdvisorResponse(response.getContent());
        return ResponseEntity.ok(advisorResponse);
    }
}
