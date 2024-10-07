package com.fresh.security;

import com.fresh.service.UserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.headers().frameOptions().disable();
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(registry -> {
                    registry.antMatchers("/public/**").permitAll();
                    registry.antMatchers("/javax.faces.resource/**").permitAll(); // Permitir recursos de JSF
                    registry.antMatchers("/Fresh/index.xhtml**").hasRole("ADMIN");
                    registry.anyRequest().authenticated();
                })
                .formLogin(httpSecurityFormLoginConfigurer -> {
                    try {
                        httpSecurityFormLoginConfigurer
                                .loginPage("/login.xhtml")
                                .successHandler(new AuthenticationSuccessHandler())
                                .failureUrl("/login.xhtml?error=true")
                                .permitAll()
                                .and()
                                .logout()
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login.xhtml?logout") // Redirigir después de cerrar sesión
                                .permitAll();
                    } catch (Exception ex) {
                        Logger.getLogger(SecurityConfiguration.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
    }

    @Override
    public UserDetailsService userDetailsService() {
        return userService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
