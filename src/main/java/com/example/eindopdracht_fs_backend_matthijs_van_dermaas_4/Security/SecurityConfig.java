package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Security;


import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.UserDto;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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

    public SecurityConfig( JwtService service, UserRepository userRepository, MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
        this.jwtService = service;
        this.userRepository = userRepository;
    }

    @Bean
    public AuthenticationManager authenticationManager(org.springframework.security.core.userdetails.UserDetailsService udService, PasswordEncoder passwordEncoder) throws Exception {
        var providerManager = new DaoAuthenticationProvider();
        providerManager.setPasswordEncoder(passwordEncoder);
        providerManager.setUserDetailsService(udService);
        return new ProviderManager(providerManager);
    }
    @Bean
    public UserDto userDto() {return new UserDto(); }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http                .httpBasic(basic -> basic.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(
                                "/*",
                                "drankorgel",
                                "/authenticate",
                                "/particulieren",
                                "/producenten",
                                "/producten",
                                "/users/createUser",
                                "/users/createProduct",
                                "/roles").permitAll()

//                        .requestMatchers(HttpMethod.GET, "/users").hasAnyRole("USER", "ADMIN", "BREWER")
                        .requestMatchers(HttpMethod.GET, "/{username}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .requestMatchers(HttpMethod.POST, "/fileDocuments/upload").permitAll()
                        .requestMatchers(HttpMethod.POST, "/products/createProduct").permitAll()

                        .requestMatchers(HttpMethod.GET, "/producten").hasAnyRole("BREWER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/producten").hasAnyRole("BREWER", "ADMIN")

                        .requestMatchers(HttpMethod.GET, "/producten/{id}").hasAnyRole("BREWER", "ADMIN")

                        .requestMatchers(HttpMethod.GET, "/authorities").permitAll()
                        .requestMatchers(HttpMethod.POST, "/authorities").permitAll()

                        .requestMatchers(HttpMethod.GET, "/roles").permitAll()
                        .requestMatchers(HttpMethod.POST, "/roles").permitAll()
                        .anyRequest().denyAll()

                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(new JwtRequestFilter(jwtService, myUserDetailsService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}