package com.mohyehia.todo.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	private final String userQuery = "SELECT EMAIL, PASSWORD, ACTIVE FROM users WHERE EMAIL = ?";
	private final String roleQuery = "SELECT u.EMAIL, r.NAME FROM users u INNER JOIN users_roles ur ON(u.ID = ur.USER_ID) INNER JOIN roles r ON (ur.ROLE_ID = r.ID) WHERE u.EMAIL = ?";
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(userQuery)
		.authoritiesByUsernameQuery(roleQuery)		
		.passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests().antMatchers("/api/auth/*").permitAll()
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
		http.formLogin().loginPage("/api/auth/login").successHandler(successHandler());
		http.authorizeRequests().antMatchers("/api/todos/*").hasAnyRole("USER").anyRequest().authenticated()
		.and().httpBasic()
		.and()
		.headers().frameOptions().disable();
	}

	private AuthenticationSuccessHandler successHandler() {
		return new AuthenticationSuccessHandler() {
			
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				response.getWriter().append("OK");
				response.setStatus(200);
			}
		};
	}
}
