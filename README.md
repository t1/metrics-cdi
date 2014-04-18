# Metrics-CDI

Binds [Metrics](http://metrics.codahale.com) to CDI and current Servlet APIs. In a Java EE 6 war, all you need to add is this dependency and add a `beans.xml`:

	<dependency>
		<groupId>com.github.t1</groupId>
		<artifactId>metrics-cdi</artifactId>
		<version>1.0.0-SNAPSHOT</version>
	</dependency>

This activates:

* Provides the [metrics servlet](http://metrics.codahale.com/manual/servlets) at `http://<host-name>:<port>/<app-name>/-metrics`.
* Automatically instruments your [web servlets](http://metrics.codahale.com/manual/servlet) (including JAX-RS).
* Registers all of your [health checks](http://metrics.codahale.com/manual/healthchecks); just extend `com.codahale.metrics.health.HealthCheck`.
* Registers all of your [gauges](http://metrics.codahale.com/manual/core/#gauges); just extend `com.codahale.metrics.Gauge`.
* Provides [counter](http://metrics.codahale.com/manual/core/#counters) instances; just `@Inject` a `com.codahale.metrics.Counter` and call e.g. `counter.inc()`.
* Provides [timer](http://metrics.codahale.com/manual/core/#timers) instances; just `@Inject` a `com.codahale.metrics.Timer` and `try (Context time = timer.time()) { ... }`.

## Things left to do

* [JMX Gauges](http://metrics.codahale.com/manual/core/#jmx-gauges)
* [Ratio Gauges](http://metrics.codahale.com/manual/core/#ratio-gauges)
* [Cached Gauges](http://metrics.codahale.com/manual/core/#cached-gauges)
* [Derivative Gauges](http://metrics.codahale.com/manual/core/#derivative-gauges)
* [Histograms](http://metrics.codahale.com/manual/core/#histograms)