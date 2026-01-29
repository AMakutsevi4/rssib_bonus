package ru.rssib.bonus.dto;

import java.math.BigDecimal;

public record AddonsRequest(String numberCard, BigDecimal amount) {
}
