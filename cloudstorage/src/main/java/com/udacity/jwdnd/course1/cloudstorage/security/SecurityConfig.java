package com.udacity.jwdnd.course1.cloudstorage.security;


import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/*
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;
*/

@Configuration
@EnableWebSecurity
public class SecurityConfig   extends WebSecurityConfigurerAdapter {


    private AuthenticationService authenticationService;

    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        Default accept all
         */
       // http.authorizeRequests()
                //.anyRequest().permitAll();
    }

    /*
   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
              .antMatchers("/css/**", "/js/**","/signup")
              .permitAll()
              .anyRequest().authenticated();
      http.formLogin()
              .loginPage("/api/login/").permitAll();
   }
   */


   /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/signup", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated();
        http.formLogin()
                .loginPage("api/login")
                .permitAll();
        http.formLogin()
                .defaultSuccessUrl("/home", true);

        http.logout()
                .permitAll();
    }
    */




   }



