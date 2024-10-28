package com.example.spring.dispatcherservice;

import lombok.Builder;

@Builder
public record OrderDispatchedMessage(
        Long orderId
)
{}
