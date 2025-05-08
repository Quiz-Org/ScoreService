package com.example.scoreservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Map;

@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    JwtAuthenticationConverter authenticationConverter(
//            Converter<Map<String, Object>, Collection<GrantedAuthority>> authoritiesConverter) {
//        var authenticationConverter = new JwtAuthenticationConverter();
//        authenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
//            return authoritiesConverter.convert(jwt.getClaims());
//        });
//        return authenticationConverter;
//    }

    @Bean
    SecurityFilterChain resourceServerSecurityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                authz -> authz.anyRequest().authenticated()
        );
        return http.build();
    }

}
