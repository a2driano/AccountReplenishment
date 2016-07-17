package com.account.replenishment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @version 1.0
 * @autor a2driano
 * @project: AccountReplenishment
 * @since 15.07.2016
 */
@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/img/**")
                .antMatchers("/fonts/**")
                .antMatchers("/css/**")
                .antMatchers("/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
//                .antMatchers("/").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/userpage").access("hasRole('USER') or hasRole('ADMIN')")
                .antMatchers("/add").access("hasRole('ADMIN')")
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .antMatchers("/admin_journal/**").access("hasRole('ADMIN')")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/").permitAll()
                .loginProcessingUrl("/j_spring_security_check")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .defaultSuccessUrl("/userpage", true);
        http.logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);
    }
}
