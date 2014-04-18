package com.github.t1.metrics;

import javax.servlet.annotation.WebServlet;

import com.codahale.metrics.servlets.AdminServlet;

@WebServlet("/-metrics/*")
public class MetricsServlet extends AdminServlet {
    private static final long serialVersionUID = 1L;
}
