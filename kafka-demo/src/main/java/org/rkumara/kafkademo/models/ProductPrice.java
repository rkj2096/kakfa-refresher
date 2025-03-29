package org.rkumara.kafkademo.models;

import java.math.BigDecimal;

public record ProductPrice(BigDecimal price, String reason) {
} 