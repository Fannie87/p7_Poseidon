package com.nnk.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nnk.springboot.services.MyAppUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private MyAppUserDetailsService myAppUserDetailsService;
    
    /**
     * Cette méthode permet de gérer les connexions et authentifications des utilisateurs, grâce à des identifiants et des mots de passe hachés.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/bidList/**", "/rating/**", "/ruleName/**", "/trade/**", "/curvePoint/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/user/**").permitAll()
                .and()
                
                .formLogin()  //login configuration
                .defaultSuccessUrl("/bidList/list")
                .and().logout()    //logout configuration
                .logoutUrl("/app-logout")
                .logoutSuccessUrl("/login")
                .and()
                .rememberMe()
                .key("123456")
                .userDetailsService(myAppUserDetailsService)
                .and()
                .exceptionHandling() //exception handling configuration
                .accessDeniedPage("/app/error");
        return http.build();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(myAppUserDetailsService).passwordEncoder(passwordEncoder);
    }
}

