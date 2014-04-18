package com.github.t1.metrics;

import javax.inject.Inject;
import javax.servlet.annotation.WebListener;

import lombok.extern.slf4j.Slf4j;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.servlet.InstrumentedFilterContextListener;

@Slf4j
@WebListener
public class MetricRegistryContextListener extends InstrumentedFilterContextListener {
    @Inject
    private MetricRegistry metrics;

    @Override
    protected MetricRegistry getMetricRegistry() {
        log.debug("register metrics instance");
        return metrics;
    }
}
