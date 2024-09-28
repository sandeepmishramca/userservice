package com.myorg.userservice.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
public class SecurityConfigurationOriginal {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((requests) -> {
//                            try {
//                                requests
//                                        .anyRequest()
//                                        .permitAll()
//                                        .and()
//                                        .cors().disable()
//                                        .csrf().disable();
//                            } catch (Exception e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
//                );
//
//        return http.build();
//    }
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        StrictHttpFirewall firewall = new StrictHttpFirewall();
//        firewall.setAllowBackSlash(true);
//        firewall.setAllowSemicolon(true);
//        return (web) -> web.httpFirewall(firewall);
//    }

}
