package com.furniture.gatewayService.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() //Que permita los options de cualquier solitud
                .antMatchers(HttpMethod.POST, "/user/login").permitAll() //Dejamos abierta la ruta sin verificacion de jwt
                .antMatchers(HttpMethod.POST, "/user/isAdminLoggedIn").permitAll() //Igual que arriba
                .antMatchers(HttpMethod.POST, "/user/isFabricatemanLoggedIn").permitAll() //Igual que arriba
                .antMatchers(HttpMethod.POST, "/user/isSalesmanLoggedIn").permitAll() //Igual que arriba
                .antMatchers(HttpMethod.POST, "/user/isLoggedIn").permitAll()//Igual que arriba
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class); //Agregamos el filtro, para los cors
    }
}
