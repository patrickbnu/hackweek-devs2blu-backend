package com.api.hackweek.utils.mapper;

import com.api.hackweek.models.script.Script;
import com.api.hackweek.models.script.ScriptRequestDto;
import com.api.hackweek.models.script.ScriptResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ScriptMapper extends AbstractMapper<Script, ScriptRequestDto, ScriptResponseDto> {
    public ScriptMapper(ModelMapper mapper) {
        super(mapper);
    }

    @Override
    Class<Script> getEntityClass() {
        return Script.class;
    }

    @Override
    Class<ScriptRequestDto> getRequestClass() {
        return ScriptRequestDto.class;
    }

    @Override
    Class<ScriptResponseDto> getResponseClass() {
        return ScriptResponseDto.class;
    }
}
