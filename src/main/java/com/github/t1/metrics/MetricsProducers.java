package com.github.t1.metrics;

import java.lang.reflect.Member;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.*;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.*;

import lombok.extern.slf4j.Slf4j;

import com.codahale.metrics.*;
import com.codahale.metrics.health.*;
import com.github.t1.log.Logged;

@Slf4j
@Logged
@Singleton
public class MetricsProducers {
    private final MetricRegistry metrics = new MetricRegistry();
    private final HealthCheckRegistry healthCheckRegistry = new HealthCheckRegistry();

    @Inject
    private Instance<HealthCheck> healthChecks;

    @Inject
    private Instance<Gauge<?>> gauges;

    @PostConstruct
    public void init() {
        log.debug("start jms reporter");
        JmxReporter.forRegistry(metrics).build().start();

        for (HealthCheck healthCheck : healthChecks) {
            String name = healthCheck.getClass().getName();
            log.debug("register health check: {}", name);
            healthCheckRegistry.register(name, healthCheck);
        }

        for (Gauge<?> gauge : gauges) {
            String name = gauge.getClass().getName();
            log.debug("register gauge: {}", name);
            metrics.register(name, gauge);
        }
    }

    @Produces
    public MetricRegistry produceMetricRegistry() {
        return metrics;
    }

    @Produces
    public HealthCheckRegistry produceHealthCheckRegistry() {
        return healthCheckRegistry;
    }

    @Produces
    public Counter produceCounter(InjectionPoint injectionPoint) {
        return metrics.counter(name(injectionPoint));
    }

    @Produces
    public Timer produceTimer(InjectionPoint injectionPoint) {
        return metrics.timer(name(injectionPoint));
    }

    @Produces
    public Meter produceMeter(InjectionPoint injectionPoint) {
        return metrics.meter(name(injectionPoint));
    }

    private String name(InjectionPoint injectionPoint) {
        Member member = injectionPoint.getMember();
        Class<?> beanClass = member.getDeclaringClass();
        String counterName = member.getName();
        return MetricRegistry.name(beanClass, counterName);
    }
}
