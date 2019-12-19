package com.sai.easwer.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.sai.easwer.security.AuthProvider;
import com.sai.easwer.security.LoginFailureHandler;
import com.sai.easwer.security.LoginSuccessHandler;
import com.sai.easwer.security.LogoutHandler;
import com.sai.easwer.security.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthProvider authProvider;
    
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    
    @Autowired
    private LoginFailureHandler loginFailureHandler;
    
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    
    @Autowired
    private LogoutHandler logoutHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			    .csrf().disable()
			    .authorizeRequests()
			    .antMatchers("/login")
			        .permitAll()
			    .anyRequest()
			        .authenticated()
			.and()
			    .authenticationProvider(authProvider)
			    .formLogin()
			    .loginPage("/login")
			        .permitAll()
			    .failureHandler(loginFailureHandler)
			    .successHandler(loginSuccessHandler)
            .and()
                .logout()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessHandler(logoutSuccessHandler)
                .addLogoutHandler(logoutHandler)
                    .permitAll();
	}

}