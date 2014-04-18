package com.github.t1.metrics;

import javax.servlet.annotation.WebFilter;

import com.codahale.metrics.servlet.InstrumentedFilter;

@WebFilter("/*")
public class MetricsFilter extends InstrumentedFilter {}
