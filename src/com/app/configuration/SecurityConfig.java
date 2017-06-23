package com.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@Import({DispatcherServletConfig.class})
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	AuthenticationSuccessConfigure loginSuccessHandler;
	
	@Bean
	public ShaPasswordEncoder shaPasswordEncoder()
	{
		return new ShaPasswordEncoder(512);
	}
	
	
	public DriverManagerDataSource getDataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setUrl("jdbc:sqlserver://MINDFIRE-PC;DatabaseName=onlineShoppingSpring;");
		dataSource.setUsername("sa");
		dataSource.setPassword("mindfire");
		return dataSource;
	}
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{
		  auth
		  .jdbcAuthentication()
		  .passwordEncoder(shaPasswordEncoder())
	  	  .dataSource(getDataSource())
	  	  .usersByUsernameQuery("SELECT userEmail AS username , userPassword AS password , enabled from Customer where userEmail=? ")
	  	  .authoritiesByUsernameQuery("SELECT userEmail AS username , authority from Authorities where userEmail=?");
		
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception
	{
		http
		//.csrf().disable()
		.authorizeRequests()
			.antMatchers("/login.html").anonymous()
			//.antMatchers("*.html").hasAnyRole("ROLE_USER")
			.antMatchers("*.html").authenticated()
			
		.and()
		.formLogin()
			.successHandler(loginSuccessHandler)
			.loginPage("/login.html")
			.usernameParameter("userEmail")
			.passwordParameter("userPassword")
			.failureUrl("/login.html?error=true")
			
			
		.and()
		.logout()
			.logoutUrl("/logout.html")
			.deleteCookies("JSESSIONID")
		.and()
		.exceptionHandling()
			.accessDeniedPage("/index.html");
	}
	
	@Override
	public void configure(WebSecurity web)
	{
		web
		.ignoring().antMatchers("/resources/**");
	}
}
