package com.api.hackweek.models.script;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "The script itself for the question")
    private String script;

    @Schema(description = "The label for the script, this will be used to display the question in the frontend")
    private String label;
}
