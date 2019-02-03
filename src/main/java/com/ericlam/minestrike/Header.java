package com.ericlam.minestrike;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class Header implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "PUT, POST, GET, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, content-type, accept");
        response.setHeader("Content-Type", "application/json, charset=UTF-8");
        response.setHeader("Accept", "application/json");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
