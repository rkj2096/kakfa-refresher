package org.rkumara.kafkademo.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.rkumara.kafkademo.event.ProductPriceChangedEvent;
import org.rkumara.kafkademo.models.Product;
import org.rkumara.kafkademo.models.ProductPrice;
import org.rkumara.kafkademo.producer.ProductPriceChangedProducer;
import org.rkumara.kafkademo.repository.ProductRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;


@RequestMapping("/product")
@RestController()
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductPriceChangedProducer priceChangedProducer;

    @GetMapping()
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @PostMapping()
    public void postMethodName(@RequestBody Product product) {
        productRepository.save(product);
    }

    @Transactional
    @PostMapping("{code}/price")
    public void updatePrice(@PathVariable("code") String code, @RequestBody ProductPrice productPrice) throws InterruptedException, ExecutionException {
        priceChangedProducer.priceChanged(
            new ProductPriceChangedEvent(code, productPrice.price())
        );
    }
    
}
