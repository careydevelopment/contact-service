package com.careydevelopment.contact.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    
    
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		    .cors().and()
		    .csrf().disable()
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
		    .authorizeRequests() 
		    .anyRequest().access("hasAuthority('CAREYDEVELOPMENT_CRM_USER')").and()
		    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
