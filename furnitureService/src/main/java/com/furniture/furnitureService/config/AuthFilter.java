package com.furniture.furnitureService.config;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class AuthFilter extends OncePerRequestFilter {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpHeaders headers = this.getHeaders(request);//Obtenemos los headers del que solicita
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(new HashMap<>(), headers);
        ResponseEntity<Boolean> authenticationResponse = restTemplate.postForEntity("http://localhost:8080/user/verifyJWT", entity, Boolean.class);

        if(!authenticationResponse.getStatusCode().equals(HttpStatus.OK)){
            System.out.println("Hubo un error aparentemente, estamos probando");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        filterChain.doFilter(request, response);
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
}
