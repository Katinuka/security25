package com.katinuka.security25.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/*
 @author    Katinuka
 @project   security25
 @version   1.0.0
 @since     31-Mar-25
 
 @see https://github.com/Katinuka/security25
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] USER_ACCESS_LEVEL = {"USER", "ADMIN", "SUPER_ADMIN"};
    private static final String[] ADMIN_ACCESS_LEVEL = {"ADMIN", "SUPER_ADMIN"};
    private static final String[] SUPER_ADMIN_ACCESS_LEVEL = {"SUPER_ADMIN"};

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests( req -> req
                        .requestMatchers("/index.html").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/v1/movies/**").hasAnyRole(USER_ACCESS_LEVEL)
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/movies/**").hasAnyRole(SUPER_ADMIN_ACCESS_LEVEL)
                        .requestMatchers("/api/v1/movies/**").hasAnyRole(ADMIN_ACCESS_LEVEL)

                        .requestMatchers("/api/v1/movies/hello/unknown").permitAll()
                        .requestMatchers("/api/v1/movies/hello/user").hasAnyRole(USER_ACCESS_LEVEL)

                        // this is already covered
                        //.requestMatchers("/api/v1/movies/hello/admin").hasAnyRole(ADMIN_ACCESS_LEVEL)

                        // authenticated() = USER_ACCESS_LEVEL at this point, as USER is the weakest possible role.
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        var user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();

        var admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        var superAdmin = User.builder()
                .username("super_admin")
                .password(passwordEncoder().encode("super_admin"))
                .roles("SUPER_ADMIN")
                .build();


        return new InMemoryUserDetailsManager(admin, user, superAdmin);
    }
}
