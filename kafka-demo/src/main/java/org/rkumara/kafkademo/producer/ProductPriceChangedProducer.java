package org.rkumara.kafkademo.producer;

import java.util.concurrent.ExecutionException;

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

    public void priceChanged(ProductPriceChangedEvent event) throws InterruptedException, ExecutionException {
        SendResult<String, ProductPriceChangedEvent> sendResult = template.send("product-price-changes", event).get();
        log.info("Send result of price changed event: {}", sendResult);
    }
}
