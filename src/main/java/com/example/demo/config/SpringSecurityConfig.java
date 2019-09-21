package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig  extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AccessDeniedHandler accessDeniedHandler;
	
	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {

	        http.csrf().disable()
	                .authorizeRequests()
	                .antMatchers("/").permitAll()
					.antMatchers("/console/**").permitAll()
					.antMatchers("/css/**").permitAll()
					.antMatchers("/js/**").permitAll()
					.antMatchers("/", "/home", "/about").permitAll()
					.antMatchers("/admin/**").hasAnyRole("ADMIN")
					.antMatchers("/user/**").hasAnyRole("USER")
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
                    .permitAll()
						.and()
	                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	        }

	    // create two users, admin and user
	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

	        auth.inMemoryAuthentication()
	                .withUser("user").password("{noop}password").roles("USER")
	                .and()
	                .withUser("admin").password("{noop}password").roles("ADMIN");
	    }
	
	

}
