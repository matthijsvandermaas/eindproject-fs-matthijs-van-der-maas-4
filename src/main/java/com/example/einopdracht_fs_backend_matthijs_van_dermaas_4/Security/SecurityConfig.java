package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Security;


import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final MyUserDetailsService myUserDetailsService;

    public SecurityConfig(JwtService service, UserRepository userRepos, MyUserDetailsService myUserDetailsService) {
        this.jwtService = service;
        this.userRepository = userRepos;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService udService, PasswordEncoder passwordEncoder) throws Exception {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(udService);
        return new ProviderManager(auth);
    }

    @Bean
    public MyUserDetailsService myuserDetailsService() {
        return new MyUserDetailsService(this.userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/*",
                                "drankorgel",
                                "/authorities",
                                "/particulieren",
                                "/producenten",
                                "/producten",
                                "/users",
                                "/roles").permitAll()

                        .requestMatchers(HttpMethod.GET, "/producten").hasRole("BREWER")
                        .requestMatchers(HttpMethod.POST, "/producten").hasRole("BREWER")
                        .requestMatchers(HttpMethod.DELETE, "/producten").hasRole("BREWER")
                        .requestMatchers(HttpMethod.PUT, "/producten").hasRole("BREWER")

                        .requestMatchers(HttpMethod.GET, "/particulieren").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/particulieren").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/particulieren").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/particulieren").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/particulieren/id").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/particulieren/id").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/particulieren/id").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/particulieren/id").hasAnyRole("USER", "ADMIN")

                        .requestMatchers(HttpMethod.GET, "/producenten").hasAnyRole("BREWER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/producenten").hasAnyRole("BREWER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/producenten").hasAnyRole("BREWER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/producenten").hasAnyRole("BREWER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/producenten/id").hasAnyRole("BREWER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/producenten/id").hasAnyRole("BREWER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/producenten/id").hasAnyRole("BREWER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/producenten/id").hasAnyRole("BREWER", "ADMIN")

                        .requestMatchers(HttpMethod.GET, "/producten").hasAnyRole("BREWER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/producten").hasAnyRole("BREWER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/producten").hasAnyRole("BREWER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/producten").hasAnyRole("BREWER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/producten/id").hasAnyRole("BREWER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/producten/id").hasAnyRole("BREWER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/producten/id").hasAnyRole("BREWER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/producten/id").hasAnyRole("BREWER", "ADMIN")

                        .requestMatchers(HttpMethod.GET, "/authorities").permitAll()
                        .requestMatchers(HttpMethod.POST, "/authorities").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/authorities").permitAll()

                        .requestMatchers(HttpMethod.GET, "/roles").permitAll()
                        .requestMatchers(HttpMethod.POST, "/roles").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/roles").permitAll()
                        .anyRequest().denyAll()

                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(new JwtRequestFilter(jwtService, myuserDetailsService()), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    public MyUserDetailsService getMyUserDetailsService() {
        return myUserDetailsService;
    }
}