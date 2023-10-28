package com.api.hackweek.controllers;

import com.api.hackweek.models.script.ScriptRequestDto;
import com.api.hackweek.models.script.ScriptResponseDto;
import com.api.hackweek.services.ScriptService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/script")
@RequiredArgsConstructor
public class ScriptController {
    private final ScriptService scriptService;

    @GetMapping
    public ResponseEntity<List<ScriptResponseDto>> findAll() {
        return ResponseEntity.ok(scriptService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScriptResponseDto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(scriptService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ScriptResponseDto> save(@RequestBody @Valid ScriptRequestDto request) {
        return ResponseEntity.ok(scriptService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScriptResponseDto> update(@PathVariable UUID id, @RequestBody @Valid ScriptRequestDto request) {
        return ResponseEntity.ok(scriptService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        scriptService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
