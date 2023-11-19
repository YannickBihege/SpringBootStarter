package com.udacity.jwdnd.course1.cloudstorage.security;


import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig   extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;
   // private final AuthenticationService authenticationService;

    private final UserDetailsServiceImpl userDetailsService;

    /*
    public SecurityConfig(AuthenticationService authenticationService, UserDetailsServiceImpl userDetailsService) {
        this.authenticationService = authenticationService;
    }

     */

    public SecurityConfig(AuthenticationService authenticationService, UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomIdentityAuthenticationProvider customIdentityAuthenticationProvider;

    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(this.authenticationService)
    */

    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
               ;

    }
    */
    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customIdentityAuthenticationProvider);

    } */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {

       // http.authorizeRequests()
                //.anyRequest().permitAll();
        http.authorizeRequests()
                .antMatchers("/","static/css","static/js")
                //.antMatchers("/**")
                .permitAll();
    }
    *
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Remove home once implemented
        http.authorizeRequests()
                .antMatchers("/api/signup", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/api/login")
                .permitAll();

        http.formLogin()
                .defaultSuccessUrl("/api/home", true);
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





   }



