# Metrics-CDI

Binds [Metrics](http://metrics.codahale.com) to CDI (and the Servlet APIs using annotations). I.e. in a Java EE 6 `war`, all you need to add is this dependency (and add a `beans.xml`, if you don't have one, yet):

	<dependency>
		<groupId>com.github.t1</groupId>
		<artifactId>metrics-cdi</artifactId>
		<version>1.0.0</version>
	</dependency>

It's not on maven central, so you'll have to grab it from [bintray](https://bintray.com/t1/javaee-helpers/metrics-cdi/view).

## Features

* Provides the [metrics servlet](http://metrics.codahale.com/manual/servlets) at `http://<host-name>:<port>/<app-name>/-metrics`.
* Automatically instruments your [web servlets](http://metrics.codahale.com/manual/servlet) (including JAX-RS).
* Registers all of your [health checks](http://metrics.codahale.com/manual/healthchecks); just extend `com.codahale.metrics.health.HealthCheck`.
* Registers all of your [gauges](http://metrics.codahale.com/manual/core/#gauges); just extend `com.codahale.metrics.Gauge`.
* Provides [counter](http://metrics.codahale.com/manual/core/#counters) instances; just `@Inject` a `com.codahale.metrics.Counter` and call e.g. `counter.inc()`.
* Provides [timer](http://metrics.codahale.com/manual/core/#timers) instances; just `@Inject` a `com.codahale.metrics.Timer` and `try (Context time = timer.time()) { ... }`.

## Tested Platforms

It just uses Servlet 3.0 and CDI 1.0, so it should run on any Java EE 6 container, but the world is not perfect. I've tried a few things:

* JBoss 8.0.0 (Wildfly): Runs perfectly
* JBoss 7.1.1: Need to [overlay](http://maven.apache.org/plugins/maven-war-plugin/overlays.html) the `metrics-cdi` dependency.
* Glassfish 4.0: Some simple tests worked out nicely.

## Things left to do

* [JMX Gauges](http://metrics.codahale.com/manual/core/#jmx-gauges)
* [Ratio Gauges](http://metrics.codahale.com/manual/core/#ratio-gauges)
* [Cached Gauges](http://metrics.codahale.com/manual/core/#cached-gauges)
* [Derivative Gauges](http://metrics.codahale.com/manual/core/#derivative-gauges)
* [Histograms](http://metrics.codahale.com/manual/core/#histograms)
