package com.example.farmingproject.security;

import com.example.farmingproject.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(userDetailsService());

        return authProvider;
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeRequests()
                .antMatchers("/crops/delete/**").hasAuthority("ADMIN")
                .antMatchers("/crops/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/cropFertilizers/delete/**").hasAuthority("ADMIN")
                .antMatchers("/cropFertilizers/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/cropWorkTeches/delete/**").hasAuthority("ADMIN")
                .antMatchers("/cropWorkTeches/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/cropWorks/delete/**").hasAuthority("ADMIN")
                .antMatchers("/cropWorks/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/cultures/delete/**").hasAuthority("ADMIN")
                .antMatchers("/cultures/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/fertilizers/delete/**").hasAuthority("ADMIN")
                .antMatchers("/fertilizers/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/fertilizerTypes/delete/**").hasAuthority("ADMIN")
                .antMatchers("/fertilizerTypes/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/harvests/delete/**").hasAuthority("ADMIN")
                .antMatchers("/harvests/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/harvestSales/delete/**").hasAuthority("ADMIN")
                .antMatchers("/harvestSales/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/providers/delete/**").hasAuthority("ADMIN")
                .antMatchers("/providers/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/providerCultures/delete/**").hasAuthority("ADMIN")
                .antMatchers("/providerCultures/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/providerFertilizers/delete/**").hasAuthority("ADMIN")
                .antMatchers("/providerFertilizers/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/sales/delete/**").hasAuthority("ADMIN")
                .antMatchers("/sales/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/teches/delete/**").hasAuthority("ADMIN")
                .antMatchers("/teches/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/techTypes/delete/**").hasAuthority("ADMIN")
                .antMatchers("/techTypes/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/works/delete/**").hasAuthority("ADMIN")
                .antMatchers("/works/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403");

        return http.build();
    }
}
