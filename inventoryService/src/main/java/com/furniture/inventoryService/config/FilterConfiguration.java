package com.furniture.inventoryService.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.*;
import java.io.IOException;

@Configuration
public class FilterConfiguration {

    @Bean
    public Filter loggerFilter() {
        return new Filter() {
            private final Logger logger = LoggerFactory.getLogger(getClass());
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                logger.info("LOGGED");
                boolean v = false;
                if(!v){
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN);
                }
                chain.doFilter(request, response);
            }
        };
    }

}
