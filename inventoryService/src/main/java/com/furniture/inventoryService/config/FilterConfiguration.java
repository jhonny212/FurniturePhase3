package com.furniture.inventoryService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class FilterConfiguration {
    private RestTemplate restTemplate = new RestTemplate();

    @Bean
    public Filter loggerFilter() {
        return new Filter() {

            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                HttpHeaders headers = this.getHeaders((HttpServletRequest) request);//Obtenemos los headers del que solicita
                // build the request
                String aut = ((HttpServletRequest) request).getHeader("authorization");
                HttpEntity<Map<String, Object>> entity = new HttpEntity<>(new HashMap<>(), headers);
                 ResponseEntity<Boolean> authenticationResponse = restTemplate.postForEntity("http://localhost:8080/user/verifyJWT", entity, Boolean.class);

                if(!authenticationResponse.getStatusCode().equals(HttpStatus.OK)){
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN);
                }

                chain.doFilter(request, response);
            }

            private HttpHeaders getHeaders(HttpServletRequest request){
                return Collections.list(request.getHeaderNames())
                    .stream()
                    .collect(Collectors.toMap(
                            Function.identity(),
                            h -> Collections.list(request.getHeaders(h)),
                            (oldValue, newValue) -> newValue,
                            HttpHeaders::new
                    ));
            }
        };
    }

}