package com.api.hackweek.controllers;

import com.api.hackweek.models.script.Answer;
import com.api.hackweek.models.user.User;
import com.api.hackweek.services.OpenAIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/gpt")
@RequiredArgsConstructor
@Tag(name = "GPT")
public class OpenAIController {
    private final OpenAIService openAIService;

    @Operation(summary = "Get chat response by question id")
    @GetMapping("/answer/{questionId}")
    public ResponseEntity<Answer> getChatResponse(@PathVariable UUID questionId) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(openAIService.getChatResponse(questionId, principal.getId()));
    }
}
