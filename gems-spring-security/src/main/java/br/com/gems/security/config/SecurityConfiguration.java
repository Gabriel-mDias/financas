package br.com.gems.security.config;

import br.com.gems.exception.SecurityException;
import br.com.gems.security.model.entity.User;
import br.com.gems.security.service.SecurityFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final SecurityFilter securityFilter;

    /**
     * Método que desabilita a proteção CSRF (Recomendada para sessões com cookies, porém não stateless) e
     * define a politica de sessão da aplicação como stateless. Isso é importante porque essa aplicação
     * REST utilizará tokens JWT para a autenticação.
     * @param httpSecurity
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy( SessionCreationPolicy.STATELESS ))
                .authorizeHttpRequests( authorize -> {
                    authorize.requestMatchers("/gems/auth/**").permitAll()
                             .anyRequest()
                             .authenticated();
                })
                .addFilterBefore( securityFilter, UsernamePasswordAuthenticationFilter.class )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        try{
            return authenticationConfiguration.getAuthenticationManager();
        } catch (Exception e){
            throw new SecurityException("Erro ao obter AuthenticationManager", e);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
