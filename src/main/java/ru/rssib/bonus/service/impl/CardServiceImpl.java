package ru.rssib.bonus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rssib.bonus.dto.AddonsRequest;
import ru.rssib.bonus.dto.WithdrawRequest;
import ru.rssib.bonus.dto.RefundRequest;
import ru.rssib.bonus.dto.BalanceResponse;
import ru.rssib.bonus.dto.OperationResponse;
import ru.rssib.bonus.entity.Card;
import ru.rssib.bonus.entity.Operation;
import ru.rssib.bonus.enums.OperationType;
import ru.rssib.bonus.repository.CardRepository;
import ru.rssib.bonus.repository.OperationRepository;
import ru.rssib.bonus.service.CardService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final OperationRepository operationRepository;

    @Override
    @Transactional
    public void addons(AddonsRequest request) {
        Card card = getCardOrThrow(request.numberCard());

        card.setBalance(card.getBalance().add(request.amount()));
        card.setUpdatedAt(LocalDateTime.now());
        cardRepository.save(card);

        createOperation(card, OperationType.ADDONS, request.amount());
    }

    @Override
    @Transactional
    public void withdraw(WithdrawRequest request) {
        Card card = getCardOrThrow(request.numberCard());

        if (card.getBalance().compareTo(request.amount()) < 0) {
            throw new IllegalArgumentException("Недостаточно бонусов на карте для списания");
        }

        card.setBalance(card.getBalance().subtract(request.amount()));
        card.setUpdatedAt(LocalDateTime.now());
        cardRepository.save(card);

        createOperation(card, OperationType.WITHDRAW, request.amount());
    }

    @Override
    @Transactional
    public void refund(RefundRequest request) {
        Card card = getCardOrThrow(request.numberCard());

        Operation operation = operationRepository.findById(request.id())
                .orElseThrow(() -> new IllegalArgumentException("Операция по карте " + request.numberCard() + " не найдена"));

        if (!operation.getCard().getNumber().equals(request.numberCard())) {
            throw new IllegalArgumentException("Операция принадлежит другой карте");
        }

        if (operation.getType() == OperationType.ADDONS && !operation.isRefund()) {
            card.setBalance(card.getBalance().subtract(operation.getAmount()));
        } else if (operation.getType() == OperationType.WITHDRAW && !operation.isRefund()) {
            card.setBalance(card.getBalance().add(operation.getAmount()));
        } else {
            throw new IllegalArgumentException("Нельзя вернуть операцию типа REFUND");
        }

        operation.setRefund(true);
        operation.setUpdatedAt(LocalDateTime.now());
        card.setUpdatedAt(LocalDateTime.now());
        cardRepository.save(card);
    }

    @Override
    @Transactional(readOnly = true)
    public BalanceResponse getBalance(String cardNumber) {
        Card card = cardRepository.findCardByNumber(cardNumber)
                .orElseThrow(() -> new IllegalArgumentException("Карта с номером " + cardNumber + " не найдена"));

        return new BalanceResponse(cardNumber, card.getBalance());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationResponse> getHistory(String numberCard) {
        Card card = getCardOrThrow(numberCard);

        return operationRepository.findAllByCardId(card.getId()).stream()
                .map(operation -> new OperationResponse(operation.getId(),
                        operation.getType().name(),
                        operation.getAmount(),
                        operation.getCreatedAt(),
                        operation.isRefund()))
                .toList();
    }

    private Card getCardOrThrow(String numberCard) {
        return cardRepository.findCardByNumber(numberCard)
                .orElseThrow(() -> new IllegalArgumentException("Карта с номером " + numberCard + " не найдена"));
    }

    private void createOperation(Card card, OperationType type, BigDecimal amount) {
        Operation operation = new Operation();
        operation.setCard(card);
        operation.setType(type);
        operation.setAmount(amount);
        operation.setCreatedAt(LocalDateTime.now());
        operationRepository.save(operation);
    }
}
