package com.api.hackweek.controllers;

import com.api.hackweek.models.script.Answer;
import com.api.hackweek.models.user.User;
import com.api.hackweek.services.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/gpt")
@RequiredArgsConstructor
public class OpenAIController {
    private final OpenAIService openAIService;

    @GetMapping("/answer/{questionId}")
    public ResponseEntity<Answer> getChatResponse(@PathVariable UUID questionId) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(openAIService.getChatResponse(questionId, principal.getId()));
    }
}
