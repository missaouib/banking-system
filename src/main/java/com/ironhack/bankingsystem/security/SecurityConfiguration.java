package com.ironhack.bankingsystem.security;

import com.ironhack.bankingsystem.service.impl.user.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.csrf().disable();
        http.authorizeRequests()
                .mvcMatchers("/v1/accounts").hasAnyRole("ACCOUNTHOLDER","ADMIN")
                .mvcMatchers("/v1/accounts/all").hasAnyRole("ADMIN")
                .mvcMatchers("/v1/accounts/*/status").hasAnyRole("ADMIN")
                .mvcMatchers("/v1/account/**").hasAnyRole("ACCOUNTHOLDER","ADMIN")
                .mvcMatchers("/v1/transaction/").hasRole("ACCOUNTHOLDER")
                .mvcMatchers("/v1/admin/**").hasRole("ADMIN")
                .mvcMatchers("v1/thirdParty/").hasRole("THIRDPARTY")
                .anyRequest().permitAll();
    }








}
