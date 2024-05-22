//package com.MongoBoundary.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
////			.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
////            .csrf(AbstractHttpConfigurer::disable)
//            .formLogin(Customizer.withDefaults())
//            .httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
////    @Bean
////    public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        CorsConfiguration config = new CorsConfiguration();
////        config.setAllowCredentials(true);
////        config.setAllowedOrigins(List.of("http://localhost:3000/"));
////        config.setAllowedMethods(Collections.singletonList("*"));
////        config.setAllowedHeaders(Collections.singletonList("*"));
////        source.registerCorsConfiguration("/**", config);
////        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
////        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
////        return bean;
////    }
//
//}
