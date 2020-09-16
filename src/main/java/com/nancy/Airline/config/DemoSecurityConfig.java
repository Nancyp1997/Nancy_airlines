package com.nancy.Airline.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.nancy.Airline.service.UserService;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:application.properties")
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private  CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler; 
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	
	//Override 2 methods configure http sec and configure uthbuildermanger
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	//	UserBuilder users = User.withDefaultPasswordEncoder();
		auth.authenticationProvider(authenticationProvider());
		/*auth.jdbcAuthentication().dataSource(dataSource)
			.passwordEncoder(passwordEncoder())
			.usersByUsernameQuery("SELECT username,password from user"
					+ "	where username = ?")	
			.authoritiesByUsernameQuery(
					"SELECT u.username, a.authority FROM user_authorities a, user u "
					+ " WHERE u.username=? AND u.id=a.user_id"
					);*/
//		.withUser(users.username("john").password("test123").roles("PASSENGER"))
//		.withUser(users.username("susan").password("test123").roles("PASSENGER", "ADMIN"));
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").hasRole("EMPLOYEE")
			.antMatchers("/leaders/**").hasRole("MANAGER")
			.antMatchers("/systems/**").hasRole("ADMIN")
			.antMatchers("/*.css").permitAll()
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.successHandler(customAuthenticationSuccessHandler)
				.permitAll()
			
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
		
		/*http.authorizeRequests()
		.antMatchers("/registration**").permitAll()
		//.anyRequest().authenticated()
		.antMatchers("/passengers/delete").hasRole("ADMIN")
		.antMatchers("/passengers/**").hasRole("PASSENGER")
		.antMatchers("/resources/**").permitAll()
		.and()
		.formLogin()
			.loginPage("/showMyLoginPage")
			.loginProcessingUrl("/authenticateTheUser")
			.defaultSuccessUrl("/home", true)
			.permitAll()
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/showMyLoginPage").permitAll();*/
	}
	
	//authenticationProvider bean definition
		@Bean
		public DaoAuthenticationProvider authenticationProvider() {
			DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
			auth.setUserDetailsService(userService); //set the custom user details service
			auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
			return auth;
		}
		 
}
