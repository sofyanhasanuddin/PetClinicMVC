package org.sofyan.latihan;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
				.antMatchers("/page/**")
					.authenticated()
			.and()
				.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/j_spring_security_check")
				.usernameParameter("username")
				.passwordParameter("password")
				.failureUrl("/login?login_error=1")
				.defaultSuccessUrl("/page/index")
				.permitAll()
			.and()
				.authorizeRequests()
				.antMatchers("/resources/**")
				.permitAll()
			.and()
				.logout()
					.logoutUrl("/logout")
					.logoutSuccessUrl("/login")
			.and()
				.csrf()
				.disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		
		auth.inMemoryAuthentication()
				.withUser("sofyan")
				.password("qwerty")
				.roles("USER");
		
	}

}
