package com.whatsapp.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;


@Configuration
public class AppConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll())
                .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
                .cors(cors -> cors.configurationSource(request -> {
                        CorsConfiguration cfg = new CorsConfiguration();
                        cfg.setAllowedOrigins(Arrays.asList(
                                "http://localhost:3000/"
                        ));
                        cfg.setAllowedMethods(Collections.singletonList("*"));
                        cfg.setAllowCredentials(true);
                        cfg.setAllowedHeaders(Collections.singletonList("*"));
                        cfg.setExposedHeaders(Arrays.asList("Authorization"));
                        cfg.setMaxAge(3600L);
                        return cfg;
                }))
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }




        /**
         *  @Bean
         *     public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
         *         http.csrf(AbstractHttpConfigurer::disable)
         *                 .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
         *                 .authorizeHttpRequests(auth -> auth.
         *                                 requestMatchers("/api/**").authenticated()
         *                                 .anyRequest().permitAll())
         *                 .addFilterBefore(null,null)
         *                 .cors().configurationSource(new CorsConfigurationSource() {
         *                     @Override
         *                     public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
         *                         return null;
         *                     }
         *                 }).and().formLogin().and().httpBasic();
         *         return http.build();
         */

        //       http .sessionManagement((session) -> session
//               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//       );
//        http.authorizeHttpRequests((authz) ->authz.anyRequest().authenticated())
//                .httpBasic(Customizer.withDefaults());
//        http
//                .authorizeHttpRequests( auth -> auth
//                        .requestMatchers("/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .httpBasic(withDefaults());



}
/**
 *   // Configuring HttpSecurity
 *     @Bean
 *     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
 *         http
 *             .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity
 *             .authorizeHttpRequests(auth -> auth
 *                 .requestMatchers("/auth/welcome").permitAll() // Permit all access to /auth/welcome
 *                 .requestMatchers("/auth/user/**").authenticated() // Require authentication for /auth/user/**
 *                 .requestMatchers("/auth/admin/**").authenticated() // Require authentication for /auth/admin/**
 *             )
 *             .formLogin(withDefaults()); // Enable form-based login
 *
 *         return http.build();
 *     }
 *
 *     // Password Encoding
 *     @Bean
 *     public PasswordEncoder passwordEncoder() {
 *         return new BCryptPasswordEncoder();
 *     }
 */