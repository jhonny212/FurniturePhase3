package com.furniture.adminService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

//@Configuration
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
                configureCors((HttpServletRequest) request, (HttpServletResponse) response);
                HttpEntity<Map<String, Object>> entity = new HttpEntity<>(new HashMap<>(), headers);
                ResponseEntity<Boolean> authenticationResponse = restTemplate.postForEntity("http://localhost:8080/user/verifyJWT", entity, Boolean.class);

                if(!authenticationResponse.getStatusCode().equals(HttpStatus.OK)){
                    System.out.println("Hubo un error aparentemente, estamos probando");
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN);
                }

                chain.doFilter(request, response);
            }

            private void configureCors(HttpServletRequest request, HttpServletResponse response) {
                response.setHeader("Access-Control-Allow-Origin", getOriginFromHeader(request));
                response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
                response.setHeader("Access-Control-Max-Age", "3600");
                response.setHeader("Access-Control-Allow-Credentials", "true");
                response.setHeader("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
            }

            private String getOriginFromHeader(HttpServletRequest request){
                String clientOrigin = request.getHeader("origin");
                return (clientOrigin == null)? "*":clientOrigin;
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
