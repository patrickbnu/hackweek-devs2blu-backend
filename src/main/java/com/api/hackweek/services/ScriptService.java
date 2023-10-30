package com.api.hackweek.services;

import com.api.hackweek.models.script.Script;
import com.api.hackweek.models.script.ScriptRequestDto;
import com.api.hackweek.models.script.ScriptResponseDto;
import com.api.hackweek.repositories.ScriptRepository;
import com.api.hackweek.utils.constants.ErrorMessages;
import com.api.hackweek.utils.mapper.ScriptMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScriptService {
    private final ScriptRepository scriptRepository;
    private final ScriptMapper mapper;

    public List<ScriptResponseDto> findAll() {
        return mapper.toResponseList(scriptRepository.findAll());
    }

    public ScriptResponseDto findById(UUID id) {
        return scriptRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.SCRIPT_NOT_FOUND));
    }

    public ScriptResponseDto save(ScriptRequestDto request) {
        return mapper.toResponse(scriptRepository.save(mapper.toEntity(request)));
    }

    public ScriptResponseDto update(UUID id, ScriptRequestDto request) {
        Script script = scriptRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.SCRIPT_NOT_FOUND));

        return mapper.toResponse(
                scriptRepository.save(mapper.updateEntity(script, request))
        );
    }

    public void deleteById(UUID id) {
        if (!scriptRepository.existsById(id)) {
            throw new EntityNotFoundException(ErrorMessages.SCRIPT_NOT_FOUND);
        }

        scriptRepository.deleteById(id);
    }
}
