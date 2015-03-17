# Metrics-CDI

Binds [Metrics](http://metrics.codahale.com) to CDI (and the Servlet APIs using annotations). I.e. in a Java EE 6 `war`, all you need to add is this dependency (and add a `beans.xml`, if you don't have one, yet):

	<dependency>
		<groupId>com.github.t1</groupId>
		<artifactId>metrics-cdi</artifactId>
		<version>1.0.0</version>
	</dependency>

It's not on maven central, so you'll have to grab it from [bintray](https://bintray.com/t1/javaee-helpers/metrics-cdi/view).

Note to self: There's an alternative: https://github.com/astefanutti/metrics-cdi

## Features

* Provides the [metrics servlets](https://dropwizard.github.io/metrics/3.1.0/manual/servlets) at `http://<host-name>:<port>/<app-name>/-metrics`.
* Automatically instruments your [web servlets](https://dropwizard.github.io/metrics/3.1.0/manual/servlet) (including JAX-RS).
* Registers all of your [health checks](https://dropwizard.github.io/metrics/3.1.0/manual/healthchecks); just extend `com.codahale.metrics.health.HealthCheck`.
* Registers all of your [gauges](https://dropwizard.github.io/metrics/3.1.0/manual/core/#gauges); just extend `com.codahale.metrics.Gauge`.
* Provides [counter](https://dropwizard.github.io/metrics/3.1.0/manual/core/#counters) instances; just `@Inject` a `com.codahale.metrics.Counter` and call e.g. `counter.inc()`.
* Provides [timer](https://dropwizard.github.io/metrics/3.1.0/manual/core/#timers) instances; just `@Inject` a `com.codahale.metrics.Timer` and `try (Context time = timer.time()) { ... }`.

## Tested Platforms

It just uses Servlet 3.0 and CDI 1.0, so it should run on any Java EE 6 container, but the world is not perfect. I've tried a few things:

* JBoss 8.0.0 (Wildfly): Runs perfectly
* JBoss 7.1.1: Need to [overlay](http://maven.apache.org/plugins/maven-war-plugin/overlays.html) the `metrics-cdi` dependency.
* Glassfish 4.0: Some simple tests worked out nicely.

## Things left to do

* [JMX Gauges](https://dropwizard.github.io/metrics/3.1.0/manual/core/#jmx-gauges)
* [Ratio Gauges](https://dropwizard.github.io/metrics/3.1.0/manual/core/#ratio-gauges)
* [Cached Gauges](https://dropwizard.github.io/metrics/3.1.0/manual/core/#cached-gauges)
* [Derivative Gauges](https://dropwizard.github.io/metrics/3.1.0/manual/core/#derivative-gauges)
* [Histograms](https://dropwizard.github.io/metrics/3.1.0/manual/core/#histograms)
