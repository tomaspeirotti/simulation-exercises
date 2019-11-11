package com.simulation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .cors()
        .configurationSource(corsConfigurationSource())
        .and()
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic();
  }

  @Bean
  @Override
  public UserDetailsService userDetailsService() {

    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    final User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);
    UserDetails user = userBuilder.username("natale").password("normaluser").roles("USER").build();

    UserDetails admin =
        userBuilder.username("admin").password("admin").roles("USER", "ADMIN").build();

    return new InMemoryUserDetailsManager(user, admin);
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource corsConfiguration = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("http://localhost:8080");
    config.addAllowedHeader("Content-Type");
    config.addAllowedHeader("Authorization");
    config.addAllowedHeader("Content-Disposition");

    // TODO FOR FUTURE VERSIONS => CHECK INJECTION OF PROPERTIES IN THE CORS CONFIGURATION
    config.addAllowedMethod("GET");
    config.addAllowedMethod("POST");
    config.addAllowedMethod("DELETE");
    config.addAllowedMethod("PUT");
    config.addAllowedMethod("HEAD");
    config.addAllowedMethod("OPTIONS");
    config.addAllowedMethod("PATCH");
    config.setMaxAge(3600L);

    config.addExposedHeader("Access-Control-Allow-Origin");
    config.addExposedHeader("Access-Control-Allow-Credentials");
    config.addExposedHeader("Authorization");
    config.addExposedHeader("Content-Disposition");

    corsConfiguration.registerCorsConfiguration("/**", config);
    return corsConfiguration;
  }
}
