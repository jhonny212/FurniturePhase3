package com.furniture.adminService.config;

import com.furniture.adminService.Model.Profile;
import com.furniture.adminService.Util.CONST;
import io.jsonwebtoken.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final String SECRET = "mySecretKey";

    //Metodos de configuración de la clase -----------------------------------------------------------------------------------------
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException {
        configureCors(request, response);
        try{
            if(checkJWTToken(request, response)){
                Claims claims = getClaimsFromToken(request.getHeader(CONST.AUTHORIZATION_HEADER.value()));
                if(claims.get("authorities") != null){
                    setUpSpringAuthentication(claims);
                }else{
                    SecurityContextHolder.clearContext();
                }
            }else{
                SecurityContextHolder.clearContext();
            }
            chain.doFilter(request, response);
        }catch(ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | HttpMessageNotWritableException | ServletException e){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        }
    }

    private void setUpSpringAuthentication(Claims claims){
        @SuppressWarnings("unchecked")
        List<String> authorities = (List<String>) claims.get("authorities");
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private boolean checkJWTToken(HttpServletRequest request, HttpServletResponse response){
        String authenticationHeader = request.getHeader(CONST.AUTHORIZATION_HEADER.value());
        if (authenticationHeader == null)
            return false;
        return true;
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

    public String generateToken(String username, int user_type, int id_user){
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");
        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("username",username)
                .claim("user_type",user_type)
                .claim("id_user",id_user)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(
                        SignatureAlgorithm.HS512,
                        SECRET.getBytes())
                .compact();
        return token;
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
}
