package com.furniture.saleService.config;

import com.furniture.saleService.Model.Profile;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;

//@Order(Ordered.HIGHEST_PRECEDENCE)
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final String SECRET = "mySecretKey";

    //Metodos de configuración de la clase -----------------------------------------------------------------------------------------
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException {
        HttpHeaders headers = this.getHeaders((HttpServletRequest) request);//Obtenemos los headers del que solicita
        // build the request
        String aut = request.getHeader("authorization");
        configureCors(request, response);
        try {
            chain.doFilter(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    //Metodos de la clase para configuración interna -------------------------------------------------------------------------------

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

    //Metodos de la clase para manipulación de tokens ------------------------------------------------------------------------------

    private Claims getClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token).getBody();
    }

    public Profile getProfileFromToken(String token){
        Claims claims = getClaimsFromToken(token);
        Profile profile = new Profile(
                (Integer)claims.get("id_user"),
                (String)claims.get("username"),
                null,null,null,
                (Integer)claims.get("user_type")
        );
        return profile;
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
