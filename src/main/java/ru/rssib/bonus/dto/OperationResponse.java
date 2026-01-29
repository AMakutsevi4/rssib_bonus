package ru.rssib.bonus.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OperationResponse(Long id, String type, BigDecimal amount, LocalDateTime createdAt, boolean refund) {
}
