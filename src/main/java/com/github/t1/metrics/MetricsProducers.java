package com.github.t1.metrics;

import static com.codahale.metrics.MetricRegistry.*;
import static com.github.t1.log.LogLevel.*;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.*;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.*;

import lombok.extern.slf4j.Slf4j;

import com.codahale.metrics.*;
import com.codahale.metrics.health.*;
import com.github.t1.log.Logged;

@Slf4j
@Singleton
public class MetricsProducers {
    private final MetricRegistry metrics = new MetricRegistry();
    private final HealthCheckRegistry healthCheckRegistry = new HealthCheckRegistry();

    @Inject
    private Instance<HealthCheck> healthChecks;

    @Inject
    private Instance<Gauge<?>> gauges;

    @PostConstruct
    public void startMetricsJmsReporter() {
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

    @Logged
    @Produces
    public MetricRegistry produceMetricRegistry() {
        return metrics;
    }

    @Logged
    @Produces
    public HealthCheckRegistry produceHealthCheckRegistry() {
        return healthCheckRegistry;
    }

    @Produces
    @Logged(level = OFF)
    public Counter produceCounter(InjectionPoint injectionPoint) {
        Class<?> beanClass = injectionPoint.getBean().getBeanClass();
        String counterName = injectionPoint.getMember().getName();
        log.debug("create counter {} in {}", counterName, beanClass);
        return metrics.counter(name(beanClass, counterName));
    }

    @Produces
    @Logged(level = OFF)
    public Timer produceTimer(InjectionPoint injectionPoint) {
        Class<?> beanClass = injectionPoint.getBean().getBeanClass();
        String timerName = injectionPoint.getMember().getName();
        log.debug("create timer {} in {}", timerName, beanClass);
        return metrics.timer(name(beanClass, timerName));
    }

    @Produces
    @Logged(level = OFF)
    public Meter produceMeter(InjectionPoint injectionPoint) {
        Class<?> beanClass = injectionPoint.getBean().getBeanClass();
        String meterName = injectionPoint.getMember().getName();
        log.debug("create meter {} in {}", meterName, beanClass);
        return metrics.meter(name(beanClass, meterName));
    }
}
