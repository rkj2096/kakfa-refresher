package org.rkumara.kafkademo.event;

import java.math.BigDecimal;

public record ProductPriceChangedEvent(String productCode, BigDecimal price) {
}
