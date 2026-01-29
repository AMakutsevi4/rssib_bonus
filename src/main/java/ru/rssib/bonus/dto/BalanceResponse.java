package ru.rssib.bonus.dto;

import java.math.BigDecimal;

public record BalanceResponse(String cardNumber, BigDecimal balance) {
}
