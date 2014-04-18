package com.github.t1.metrics;

import javax.inject.Inject;
import javax.servlet.annotation.WebListener;

import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.servlets.HealthCheckServlet;

@WebListener
public class HealthCheckRegistryWebListener extends HealthCheckServlet.ContextListener {
    @Inject
    private HealthCheckRegistry metrics;

    @Override
    protected HealthCheckRegistry getHealthCheckRegistry() {
        return metrics;
    }
}
