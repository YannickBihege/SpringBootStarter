package com.udacity.jwdnd.course1.cloudstorage.config;


import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    private AuthenticationService authenticationService;

    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(this.authenticationService);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * Corresponds to the authorizations
         */
        http.authorizeRequests()
                .antMatchers("/api/signup", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/api/login")
                .permitAll();

        http.formLogin()
                .defaultSuccessUrl("/api/home", true);
        http.logout();

    }


    @Bean
    public PasswordEncoder getPasswordEncoder(){return NoOpPasswordEncoder.getInstance();}

    }







