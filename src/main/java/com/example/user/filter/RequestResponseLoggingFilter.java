package com.example.user.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class RequestResponseLoggingFilter implements jakarta.servlet.Filter {
    private static final Logger logger =
            LoggerFactory.getLogger(RequestResponseLoggingFilter.class);
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String method = req.getMethod();
        String uri = req.getRequestURI();

        System.out.println("=== REQUEST START ===");
        System.out.println("Time: " + LocalDateTime.now());
        System.out.println("Method: " + method);
        System.out.println("URI: " + uri);

        logger.info("=== REQUEST START ===");
        logger.info("Method: {}", req.getMethod());
        logger.info("URI: {}", req.getRequestURI());

        chain.doFilter(request, response);

        System.out.println("=== REQUEST END ===");
        System.out.println("Completed: " + uri);

        logger.info("=== REQUEST END ===");
        logger.info("Completed: " + uri);
    }
}