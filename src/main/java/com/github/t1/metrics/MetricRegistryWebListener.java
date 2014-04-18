package com.github.t1.metrics;

import javax.inject.Inject;
import javax.servlet.annotation.WebListener;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.servlets.MetricsServlet;

@WebListener
public class MetricRegistryWebListener extends MetricsServlet.ContextListener {
    @Inject
    private MetricRegistry metrics;

    @Override
    protected MetricRegistry getMetricRegistry() {
        return metrics;
    }
}
