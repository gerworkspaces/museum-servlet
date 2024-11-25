package net.blwsmartware.museum.configuration;

import net.blwsmartware.museum.security.JwtCustomDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${jwt.secret}")
    private String SIGNER_KEY;

    private static final String[] PUBLIC_ENDPOINTS = {
            "/users",
            "/auth/**",
    };
    private final JwtCustomDecoder customJwtDecoder;

    public SecurityConfig(JwtCustomDecoder customJwtDecoder) {
        this.customJwtDecoder = customJwtDecoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers(
                                HttpMethod.POST,PUBLIC_ENDPOINTS).permitAll()
                            .requestMatchers("/swagger-ui/**").permitAll()
                            .requestMatchers("/swagger-ui/**").permitAll()
                            .requestMatchers("/v3/**").permitAll()
                                .anyRequest().authenticated();
                    } )
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception->exception.authenticationEntryPoint(new JwtAuthEntryPoint()))

                .oauth2ResourceServer(oauth2 -> {
                    oauth2.jwt(jwtConfigurer ->
                                    jwtConfigurer.decoder(customJwtDecoder)
                                        .jwtAuthenticationConverter(jwtAuthenticationConverter())
                                )

                            .authenticationEntryPoint(new JwtAuthEntryPoint())
                    ;

                }
                )  ;

        return http.build();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter granted = new JwtGrantedAuthoritiesConverter();
        granted.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter converter =  new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(granted);
         return converter;
    }

}

