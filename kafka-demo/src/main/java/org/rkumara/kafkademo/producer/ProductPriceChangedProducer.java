package org.rkumara.kafkademo.producer;

import java.util.concurrent.CompletableFuture;
import org.rkumara.kafkademo.event.ProductPriceChangedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductPriceChangedProducer {
    
    private final KafkaTemplate<String, ProductPriceChangedEvent> template;

    public void priceChanged(ProductPriceChangedEvent event) {
        CompletableFuture<SendResult<String, ProductPriceChangedEvent>> result = template.send("product-price-changes", event);
        result.whenComplete((sendResult, ex) -> {
            if (ex != null) {
                log.error("Error publishing event: {}", ex.getMessage());
            } else {
                log.info("Event published successfully: {}", sendResult.getProducerRecord().value());
            }
        });
    }
}
