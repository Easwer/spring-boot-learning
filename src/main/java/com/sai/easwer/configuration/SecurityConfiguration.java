package com.sai.easwer.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.sai.easwer.security.AuthAccessDeniedHandler;
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
    private AuthAccessDeniedHandler accessDeniedHandler;
//    
//    @Autowired
//    private LogoutHandler logoutHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
	    auth.authenticationProvider(authProvider);
	}
	
	

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
			    .formLogin()
			    .loginPage("/login")
			        .permitAll()
			    .failureHandler(loginFailureHandler)
			    .successHandler(loginSuccessHandler)
            .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout-success")
                .logoutSuccessHandler(logoutSuccessHandler)
//                .addLogoutHandler(logoutHandler)
                    .permitAll();
            
		http
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .accessDeniedPage("/unauthorized");
		
		http
		        .headers()
		        .frameOptions()
		        .sameOrigin();
	}

}