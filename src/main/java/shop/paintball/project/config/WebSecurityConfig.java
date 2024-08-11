package shop.paintball.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import shop.paintball.project.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/showRegistrationPage", "/showAuthorizationPage", "/registerUser", "/resources/**").permitAll()
                        .requestMatchers("/showOrderProcessing","/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .rememberMe(rememberMe -> rememberMe
                        .key("mySecretKey")
                        .rememberMeParameter("remember-me")
                        .tokenValiditySeconds(86400)
                )
                .formLogin(form -> form
                        .loginPage("/showAuthorizationPage")
                        .loginProcessingUrl("/login")
                        .usernameParameter("login")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/showAuthorizationPage")
                        .permitAll()
                );

        return http.build();

    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
