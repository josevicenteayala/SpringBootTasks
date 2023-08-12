package com.task.crud.observability;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {
    /**
     * Actual health check logic.
     *
     * @param builder the {@link Builder} to report health status and details
     * @throws Exception any {@link Exception} that should create a {@link Status#DOWN}
     *                   system status.
     */
    @Override
    protected void doHealthCheck(Builder builder) throws Exception {
        builder.up();
    }
}
