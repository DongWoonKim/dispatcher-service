package com.example.spring.dispatcherservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.function.Function;

@FunctionalSpringBootTest
class DispatchingFunctionsIntegrationsTest {

    @Autowired
    private FunctionCatalog catalog;

    @Test
    void packAndLabelOrder() {
        Function<OrderAcceptedMessage, Flux<OrderDispatchedMessage>> packAndLabel = catalog.lookup(Function.class, "pack|label");
        long orderId = 121L;

        StepVerifier
                .create(packAndLabel.apply(new OrderAcceptedMessage(orderId)))
                .expectNextMatches(
                        dispatchedOrder -> dispatchedOrder.equals(new OrderDispatchedMessage(orderId))
                )
                .verifyComplete();
    }

}