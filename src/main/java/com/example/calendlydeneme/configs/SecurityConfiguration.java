package com.example.calendlydeneme.configs;

import com.example.calendlydeneme.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    private final UserServiceImpl userServiceImpl;

    @Autowired
    AuthenticationSuccessHandler successHandler;

    @Autowired
    public SecurityConfiguration(@Lazy UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userServiceImpl);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    /*@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(
                        "/registration**",
                        "/js/**",
                        "/css/**",
                        "/img/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();
        return http.build();
    }*/


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http
                //.csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/registration**",
                        "/js/**",
                        "/css/**",
                        "/img/**").permitAll()
                .antMatchers("/customerLogin").hasAnyAuthority("Customer","Admin")
                .antMatchers("/adminLogin").hasAnyAuthority("Admin")
                .antMatchers("/doctorLogin").hasAnyAuthority("Doctor")
                .antMatchers("/assistantLogin").hasAnyAuthority("Assistant")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login")
                .permitAll()
                .successHandler(successHandler)
                .and().logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();
        return http.build();

    }


}

