package com.api.hackweek.models.account;

import com.api.hackweek.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private Double income;

    @Column(nullable = false)
    private Double expenses;

    @OneToOne(mappedBy = "account")
    private User user;

    public Account(Double income, Double expenses) {
        this.income = income;
        this.expenses = expenses;
    }
}
