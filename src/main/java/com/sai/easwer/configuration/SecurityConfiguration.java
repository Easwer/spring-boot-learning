// package com.sai.easwer.configuration;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// import com.sai.easwer.security.AuthAccessDeniedHandler;
// import com.sai.easwer.security.AuthProvider;
// import com.sai.easwer.security.LoginFailureHandler;
// import com.sai.easwer.security.LoginSuccessHandler;
// import com.sai.easwer.security.LogoutSuccessHandler;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//     @Autowired
//     private AuthProvider authProvider;
    
//     @Autowired
//     private LoginSuccessHandler loginSuccessHandler;
    
//     @Autowired
//     private LogoutSuccessHandler logoutSuccessHandler;
    
//     @Autowired
//     private AuthAccessDeniedHandler accessDeniedHandler;
// //    
// //    @Autowired
// //    private LogoutHandler logoutHandler;
    
//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception
//     {
//         auth.authenticationProvider(authProvider);
//     }
    
    
//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http
//                 .csrf().disable()
//                 .authorizeRequests()
//                 .antMatchers("/login-page")
//                     .permitAll()
//                 .antMatchers("/logout-success")
//                     .permitAll()
//                 .anyRequest()
//                     .authenticated()
//             .and()
//                 .formLogin()
//                 .loginPage("/login-page")
//                     .permitAll()
// //                .failureHandler(loginFailureHandler)
//                 .successHandler(loginSuccessHandler)
//             .and()
//                 .logout()
// //                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                     .invalidateHttpSession(true)
//                     .clearAuthentication(true)
//                     .permitAll()
//                 .logoutUrl("/logout")
//                     .permitAll()
//                 .logoutSuccessHandler(logoutSuccessHandler);
            
//         //Exception handling
//         http
//                 .exceptionHandling()
//                 .accessDeniedHandler(accessDeniedHandler)
//                 .accessDeniedPage("/unauthorized");
        
//         // To view H2 console
//         http
//                 .headers()
//                 .frameOptions()
//                 .sameOrigin();
        
//         // To use HTTPS
//         http
//                 .requiresChannel()
//                 .anyRequest()
//                 .requiresSecure();  
//     }

// }