package com.api.hackweek.models.pluggy;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionMonthResponse {
    private UUID id;
    private String description;
    private double amount;
    private LocalDate date;
    private String category;
}
