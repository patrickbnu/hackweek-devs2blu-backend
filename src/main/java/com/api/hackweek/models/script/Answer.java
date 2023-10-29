package com.api.hackweek.models.script;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer {
    @Schema(description = "The question id, which is the same as the script id, you can find all the scripts ids in the Find All Scripts endpoint")
    private UUID questionId;

    @Schema(description = "The answer to the question")
    private String answer;
}
