package com.alkbashi.smartrestaurant.demo.security;

import com.alkbashi.smartrestaurant.demo.security.users.userService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class config extends WebSecurityConfigurerAdapter
{
    @Autowired
    private final userService userService;

    @Autowired
    private final PasswordEncodingClass passwordEncodingClass;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable().authorizeRequests()

                .antMatchers("/register/**").permitAll()

                .anyRequest().authenticated().and()

                .formLogin().successForwardUrl("/customer/success").failureForwardUrl("/customer/fail");
    }


    @Bean
    public DaoAuthenticationProvider authProvider()
    {
        DaoAuthenticationProvider daoAP = new DaoAuthenticationProvider();

        daoAP.setPasswordEncoder(passwordEncodingClass.encoder());
        daoAP.setUserDetailsService(userService);

        return daoAP;
    }


}
