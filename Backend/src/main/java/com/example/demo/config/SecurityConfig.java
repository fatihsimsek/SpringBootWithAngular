package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.security.JwtAuthenticationEntryPoint;
import com.example.demo.security.JwtTokenFilterConfigurer;
import com.example.demo.security.JwtTokenProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtAuthenticationEntryPoint unauthorizedHandler;

    private JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider, JwtAuthenticationEntryPoint unauthorizedHandler) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.unauthorizedHandler = unauthorizedHandler;
    }
    
    @Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .cors().and()
        .csrf().disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        http.authorizeRequests()
            .antMatchers("/auth/**").permitAll()
            .anyRequest().authenticated();
       
        http.exceptionHandling()
            .authenticationEntryPoint(unauthorizedHandler);
        
        // Apply JWT
        http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
      // Allow swagger to be accessed without authentication
      web.ignoring().antMatchers("/v2/api-docs")
          .antMatchers("/swagger-resources/**")
          .antMatchers("/swagger-ui.html")
          .antMatchers("/configuration/**")
          .antMatchers("/webjars/**")
          .antMatchers("/public");
    }
}
