package com.horadorango.projetohoradorango.core;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtTokenFilter jwtFilter, CorsConfigurationSource corsConfigurationSource) throws Exception {
//        return httpSecurity.cors(cors -> cors.configurationSource(corsConfigurationSource))
//                .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**", "/device/**", "**").permitAll().anyRequest().authenticated())
//                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//                .csrf(AbstractHttpConfigurer::disable)
//                .build();
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
//        corsConfiguration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
//
//        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//
//        return urlBasedCorsConfigurationSource;
//    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedHeaders("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
            }
        };
    }

}
