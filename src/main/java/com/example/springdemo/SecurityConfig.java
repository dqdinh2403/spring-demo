package com.example.springdemo;

import com.example.springdemo.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // JDBC-based user store
//    @Autowired
//    DataSource dataSource;

    // Customizing user authentication
//    @Autowired
//    private UserDetailsService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // In-memory user store
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("qoobee")
                .password(encoder.encode("qoobee"))
                .authorities("USER");

        // JDBC-based user store
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username, password, enabled from Users where username = ?")
//                .authoritiesByUsernameQuery("select username, authority from UserAuthorities where username = ?")
//                .passwordEncoder(new StandardPasswordEncoder("secretKey"));

        // LDAP-backed user store
//        auth.ldapAuthentication()
//                .userSearchBase("ou=people")
//                .userSearchFilter("(uid={0})")
//                .groupSearchBase("ou=groups")
//                .groupSearchFilter("{member={0}}")
//                .passwordCompare()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .passwordAttribute("passcode");

        // Customizing user authentication
//        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

//    @Bean
//    public PasswordEncoder encoder(){
//        return new StandardPasswordEncoder("secretKey");
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/design", "/orders")
//                    .hasRole("USER")
//                .antMatchers("/", "/**")
//                    .permitAll()
//                .and()
//                    .formLogin()
//                    .loginPage("/login")
//                    .loginProcessingUrl("/login")
//                    .usernameParameter("username")
//                    .passwordParameter("password")
//                    .defaultSuccessUrl("/design")
//                .and()
//                    .logout()
//                    .logoutSuccessUrl("/")
//                ;
//    }


}
