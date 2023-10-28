package com.api.hackweek.services;

import com.api.hackweek.enums.UserRole;
import com.api.hackweek.exceptions.LoginAlreadyExists;
import com.api.hackweek.models.account.Account;
import com.api.hackweek.models.pluggy.TransactionRequest;
import com.api.hackweek.models.pluggy.TransactionsPercentage;
import com.api.hackweek.models.user.*;
import com.api.hackweek.repositories.UserRepository;
import com.api.hackweek.utils.constants.ErrorMessages;
import com.api.hackweek.utils.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private PluggyService pluggyService;
    private final UserMapper mapper;

    public UserService(UserRepository userRepository, @Lazy PluggyService pluggyService, UserMapper mapper) {
        this.userRepository = userRepository;
        this.pluggyService = pluggyService;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException(ErrorMessages.USERNAME_NOT_FOUND));
    }

    public UserResponseDto save(UserRequestDto request) {
        if (userRepository.existsByLogin(request.getLogin())) {
            throw new LoginAlreadyExists();
        }

        User user = mapper.toEntity(request);

        user.setRole(UserRole.USER);

        return mapper.toResponse(
                userRepository.save(user)
        );
    }

    public UserResponseDto findById(UUID id) {
        return mapper.toResponse(userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(ErrorMessages.USER_NOT_FOUND)));
    }

    public UserResponseDto updateInvestorProfile(UserInvestorProfileDto request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new UsernameNotFoundException(ErrorMessages.USERNAME_NOT_FOUND));

        user.setInvestorProfile(request.getInvestorProfile());

        return mapper.toResponse(userRepository.save(user));
    }

    public UserResponseDto linkBankAccount(UserAccountSyncDto request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new UsernameNotFoundException(ErrorMessages.USERNAME_NOT_FOUND));

        user.setItemId(request.getBankAccountId());

        User savedUser = userRepository.save(user);

        Account account = getAccount(savedUser.getId());

        savedUser.setAccount(account);

        return mapper.toResponse(userRepository.save(savedUser));
    }

    private Account getAccount(UUID userId) {
        List<TransactionsPercentage> transactions = pluggyService.getTransactions(userId, new TransactionRequest(LocalDate.now().getYear(), LocalDate.now().getMonthValue()));

        Double income = transactions.stream()
                .filter(transaction -> transaction.getTotalAmountInCategory() > 0)
                .mapToDouble(TransactionsPercentage::getTotalAmountInCategory)
                .sum();

        Double expenses = transactions.stream()
                .filter(transaction -> transaction.getTotalAmountInCategory() < 0)
                .mapToDouble(TransactionsPercentage::getTotalAmountInCategory)
                .sum();

        return new Account(income, expenses);
    }
}
