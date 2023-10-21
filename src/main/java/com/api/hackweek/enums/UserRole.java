package com.api.hackweek.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public enum UserRole {
    USER(Set.of("ROLE_USER"));

    private final Set<String> roles;
}
