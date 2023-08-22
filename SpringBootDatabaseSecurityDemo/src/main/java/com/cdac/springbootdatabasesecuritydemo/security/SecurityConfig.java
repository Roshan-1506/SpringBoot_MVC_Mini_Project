package com.cdac.springbootdatabasesecuritydemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

		@Bean
		public UserDetailsService getUserDetailsService() {
			return new UserDetailsServiceImpl();
		}
		
		@Bean
		public BCryptPasswordEncoder getBCryptPasswordEncoder() {
			return new BCryptPasswordEncoder();
		}
		
		@Bean
		public DaoAuthenticationProvider getDaoAuthenticationProvider() {
			DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
			authProvider.setUserDetailsService(getUserDetailsService());
			authProvider.setPasswordEncoder(getBCryptPasswordEncoder());
			return authProvider;
		}

		//Authentication
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			
			auth.authenticationProvider(getDaoAuthenticationProvider());
		}

		//Authorization
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			http.authorizeHttpRequests()
				.antMatchers("/").hasAnyAuthority("USER","CREATOR","EDITOR","ADMIN")
				.antMatchers("/new").hasAnyAuthority("ADMIN","CREATOR")
				.antMatchers("/edit").hasAnyAuthority("EDITOR","ADMIN")
				.antMatchers("/delete").hasAuthority("ADMIN")
				.anyRequest().authenticated()
				.and()
				.formLogin().permitAll()
				.and()
				.exceptionHandling().accessDeniedPage("/403");
		}
}





