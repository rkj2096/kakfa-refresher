package org.rkumara.kafkademo.consumer;

import org.rkumara.kafkademo.event.ProductPriceChangedEvent;
import org.rkumara.kafkademo.repository.ProductRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
class ProductPriceChangedEventHandler {
  private final ProductRepository productRepository;

  @KafkaListener(topics = "product-price-changes", groupId = "demo")
  public void handle(ProductPriceChangedEvent event) {
    productRepository.updateProductPrice(event.productCode(), event.price());
  }
}