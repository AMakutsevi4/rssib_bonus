package ru.rssib.bonus.service;

import ru.rssib.bonus.dto.*;

import java.util.List;

public interface CardService {

    void addons(AddonsRequest request);

    void withdraw(WithdrawRequest request);

    void refund(RefundRequest request);

    BalanceResponse getBalance(String cardNumber);

    List<OperationResponse> getHistory(String numberCard);
}