package ru.rssib.bonus.dto;

import java.math.BigDecimal;

public record WithdrawRequest(String numberCard, BigDecimal amount) {
}
