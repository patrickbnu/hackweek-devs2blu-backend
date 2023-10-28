package com.api.hackweek.controllers;

import com.api.hackweek.models.script.Answer;
import com.api.hackweek.services.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/gpt")
@RequiredArgsConstructor
public class OpenAIController {
    private final OpenAIService openAIService;

    @GetMapping("/financial-education")
    public ResponseEntity<Answer> getFinancialEducationResponse(@RequestParam UUID questionId, @RequestParam UUID userId) {
        return ResponseEntity.ok(openAIService.getFinancialEducationalResponse(questionId, userId));
    }
}
