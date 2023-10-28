package com.api.hackweek.models.script;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer {
    private UUID questionId;
    private String answer;
}
