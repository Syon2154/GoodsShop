package com.goods.shop.config;

import com.goods.shop.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String MANAGER = Role.RoleName.MANAGER.name();
    private static final String CLIENT = Role.RoleName.CLIENT.name();
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailsService userDetailsService,
                          PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/register")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/categories", "/products")
                .hasRole(MANAGER)
                .antMatchers(HttpMethod.GET, "/categories", "/products", "/shopping-carts/by-user")
                .hasAnyRole(MANAGER, CLIENT)
                .antMatchers(HttpMethod.PUT, "/shopping-carts/products/add")
                .hasAnyRole(MANAGER, CLIENT)
                .antMatchers(HttpMethod.GET, "/users")
                .hasRole(MANAGER)
                .antMatchers(HttpMethod.POST, "/orders/create")
                .hasAnyRole(MANAGER, CLIENT)
                .antMatchers(HttpMethod.POST, "/orders/pay")
                .hasAnyRole(MANAGER, CLIENT)
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
