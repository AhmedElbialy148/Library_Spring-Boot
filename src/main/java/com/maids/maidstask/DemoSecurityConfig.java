//package com.maids.maidstask;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DemoSecurityConfig {
//    //add support for JDBC ... no more hardcoded users
//@Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource){
//      JdbcUserDetailsManager jdbcUserDetailsManager=new JdbcUserDetailsManager(dataSource);
//      //define query to retrieve user by username
//    jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");
//
//    //define query to retrieve authentication/role by username
//jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");
//        return jdbcUserDetailsManager;
//    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http.authorizeHttpRequests(configurer->
//                configurer
//                        .requestMatchers("/api/books/**").permitAll()
//                        .requestMatchers("/api/borrow/**").permitAll()
//                        .requestMatchers("/api/return/**").permitAll()
//                        .requestMatchers(HttpMethod.POST,"/api/patrons/login").permitAll()
//                        .requestMatchers(HttpMethod.POST,"/api/patrons/signup").permitAll()
//                        .anyRequest().authenticated()
//                );
////use Http Basic authentication
//        http.httpBasic(Customizer.withDefaults());
//
//        //disable Cross Site Request Forgery (CSRF)
//        //IN GENERAL , not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
//        http.csrf(csrf->csrf.disable());
//        return http.build();
//    }
//
//
//    /*
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//
//        UserDetails john = User.builder()
//                .username("john")
//                .password("{noop}test123")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails mary = User.builder()
//                .username("mary")
//                .password("{noop}test123")
//                .roles("EMPLOYEE","MANAGER")
//                .build();
//
//        UserDetails susan = User.builder()
//                .username("susan")
//                .password("{noop}test123")
//                .roles("EMPLOYEE","MANAGER","ADMIN")
//                .build();
//
//return new InMemoryUserDetailsManager(john,mary,susan);
//
//    }
//*/
//}
