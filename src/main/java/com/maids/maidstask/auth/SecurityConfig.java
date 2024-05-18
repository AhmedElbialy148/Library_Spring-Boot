package com.maids.maidstask.auth;

import com.maids.maidstask.auth.Jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig  {
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService, CustomAuthEntryPoint customAuthEntryPoint, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtAuthenticationFilter =jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(HttpMethod.GET,"/api/patrons").permitAll();
                    req.requestMatchers("/api/patrons/login").permitAll();
                    req.requestMatchers("/api/patrons/register").permitAll();
                    req.requestMatchers("/api/books/**").permitAll();
                    req.requestMatchers("/api/borrow/**").permitAll();
                    req.requestMatchers("/api/return/**").permitAll();
//                    req.anyRequest().permitAll();
                    req.anyRequest().authenticated();
//                    req.requestMatchers(HttpMethod.GET, "/patrons/register").permitAll();
//                    req.requestMatchers(HttpMethod.POST, "/patrons/register").permitAll();

                })
                .userDetailsService(customUserDetailsService) // 1
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 2
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(new CustomAuthEntryPoint()))
                .build();

        // 1,2,3 are for jwt implementation, remove to use sessions instead
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails admin = User.builder()
//                .username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();
//        UserDetails user = User.builder()
//                .username("user").password(passwordEncoder().encode("user")).roles("USER").build();
//    }

    @Bean
    public AuthenticationManager authenticationManager
            (AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
