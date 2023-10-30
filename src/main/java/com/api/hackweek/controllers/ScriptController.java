package com.api.hackweek.controllers;

import com.api.hackweek.models.script.ScriptRequestDto;
import com.api.hackweek.models.script.ScriptResponseDto;
import com.api.hackweek.services.ScriptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/script")
@RequiredArgsConstructor
@Tag(name = "Script")
public class ScriptController {
    private final ScriptService scriptService;

    @Operation(summary = "Find all scripts")
    @GetMapping
    public ResponseEntity<List<ScriptResponseDto>> findAll() {
        return ResponseEntity.ok(scriptService.findAll());
    }

    @Operation(summary = "Find script by id")
    @GetMapping("/{id}")
    public ResponseEntity<ScriptResponseDto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(scriptService.findById(id));
    }

    @Operation(summary = "Save script")
    @PostMapping
    public ResponseEntity<ScriptResponseDto> save(@RequestBody @Valid ScriptRequestDto request) {
        return ResponseEntity.ok(scriptService.save(request));
    }

    @Operation(summary = "Update script")
    @PutMapping("/{id}")
    public ResponseEntity<ScriptResponseDto> update(@PathVariable UUID id, @RequestBody @Valid ScriptRequestDto request) {
        return ResponseEntity.ok(scriptService.update(id, request));
    }

    @Operation(summary = "Delete script by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        scriptService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
