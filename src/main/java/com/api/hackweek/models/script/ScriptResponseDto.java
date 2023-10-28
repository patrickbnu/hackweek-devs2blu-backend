package com.api.hackweek.models.script;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScriptResponseDto {
    private String id;
    private String script;
    private String label;
}
