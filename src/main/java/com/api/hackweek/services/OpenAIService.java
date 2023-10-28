package com.api.hackweek.services;

import com.api.hackweek.models.pluggy.TransactionRequest;
import com.api.hackweek.models.pluggy.TransactionsPercentage;
import com.api.hackweek.models.script.Answer;
import com.api.hackweek.models.script.ScriptResponseDto;
import com.api.hackweek.models.user.UserResponseDto;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OpenAIService {
    private final OpenAiService service;
    private final ScriptService scriptService;
    private final UserService userService;
    private final PluggyService pluggyService;
    private final ChatCompletionRequest.ChatCompletionRequestBuilder request;

    public Answer getChatResponse(UUID scriptId, UUID userId) {
        ScriptResponseDto script = scriptService.findById(scriptId);
        UserResponseDto user = userService.findById(userId);

        String fullScript = script.getScript() + "\n\n" + getUserScript(user);

        request.messages(List.of(
                new ChatMessage(
                        ChatMessageRole.USER.value(),
                        fullScript
                )
        ));

        return Answer.builder()
                .questionId(scriptId)
                .answer(service.createChatCompletion(request.build()).getChoices().get(0).getMessage().getContent())
                .build();
    }

    private String getUserScript(UserResponseDto user) {
        StringBuilder sb = new StringBuilder();

        LocalDate dateNow = LocalDate.now().minusMonths(1);
        List<TransactionsPercentage> transactions = pluggyService.getTransactions(user.getId(), new TransactionRequest(dateNow.getYear(), dateNow.getMonthValue()));

        sb.append("Perfil de investidor: ").append(user.getInvestorProfile()).append(",\n");
//        sb.append("Total de dinheiro na conta: ").append(user.account.getBalance()).append(",\n");
//        sb.append("Ganhos por mês: ").append(user.account.getIncome()).append(",\n");
//        sb.append("Gastos por mês: ").append(user.account.getExpenses()).append(",\n");
        sb.append("Total de dinheiro na conta: ").append(3200).append(",\n");
        sb.append("Ganhos por mês: ").append(2000).append(",\n");
        sb.append("Gastos por mês: ").append(1200).append(",\n");
        sb.append("Gastos por categoria relativos ao mês anterior:").append("\n");

        for (TransactionsPercentage transaction : transactions) {
            sb
                    .append("- ")
                    .append(transaction.getCategory())
                    .append(": ")
                    .append(transaction.getTotalAmountInCategory())
                    .append(" (equivalente a ")
                    .append(transaction.getPercentage())
                    .append("% dos ganhos no mês)")
                    .append("\n");
        }

        return sb.toString();
    }
}
