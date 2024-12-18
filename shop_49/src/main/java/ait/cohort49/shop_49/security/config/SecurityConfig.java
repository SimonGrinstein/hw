package ait.cohort49.shop_49.security.config;

import ait.cohort49.shop_49.security.filter.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    private TokenFilter tokenFilter;

    public SecurityConfig(TokenFilter tokenFilter) {
        this.tokenFilter = tokenFilter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer :: disable)
                .sessionManagement(ses -> ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//IF_REQUIRED
                .httpBasic(AbstractHttpConfigurer::disable)//withDefaults
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(HttpMethod.POST,"/auth/login" ,"/auth/refresh").permitAll()
                        .requestMatchers(HttpMethod.GET, "/products").permitAll()
                        .requestMatchers(HttpMethod.GET,  "/products/{id}").authenticated()
                        //.requestMatchers(HttpMethod.GET,  "/products/{id}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST,  "/products").hasRole("ADMIN")
                        //.requestMatchers(HttpMethod.GET,  "/customer/count").permitAll()
                        //.requestMatchers(HttpMethod.POST,  "/customer/{id}").permitAll()
                        //.requestMatchers(HttpMethod.POST,  "/customer").hasRole("ADMIN")
                        //.requestMatchers(HttpMethod.GET,  "/customer").hasRole("ADMIN")
                        .anyRequest().authenticated()

                );

        return http.build();
    }
}
