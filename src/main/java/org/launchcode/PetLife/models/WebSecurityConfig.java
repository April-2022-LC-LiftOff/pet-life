package org.launchcode.PetLife.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.Arrays;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Autowired
        private DataSource dataSource;

        @Bean
        public UserDetailsService userDetailsService() {
            return new CustomUserDetailsService();
        }

        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }


//    @Bean
//    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
//        StrictHttpFirewall firewall = new StrictHttpFirewall();
//        firewall.setAllowUrlEncodedSlash(true);
//        return firewall;
//    }
        @Bean
        public DaoAuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(userDetailsService());
            authProvider.setPasswordEncoder(passwordEncoder());

            return authProvider;
        }


        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(authenticationProvider());
        }

//
//    @Bean
//    public HttpFirewall looseHttpFirewall() {
//        StrictHttpFirewall firewall = new StrictHttpFirewall();
//        firewall.setAllowedHttpMethods(Arrays.asList("GET", "POST"));
//        firewall.setAllowSemicolon(true);
//        firewall.setAllowUrlEncodedSlash(true);
//        firewall.setAllowBackSlash(true);
//        firewall.setAllowUrlEncodedPercent(true);
//        firewall.setAllowUrlEncodedPeriod(true);
//        return firewall;
//    }


//    @Bean
//    HttpFirewall httpFirewall() {
//        StrictHttpFirewall firewall = new StrictHttpFirewall();
//        firewall.setAllowUrlEncodedDoubleSlash(true);
//        return firewall;
//    }
//@Override
//    private void rejectedBlacklistedUrls(HttpServletRequest request) {
//        for (String forbidden : this.encodedUrlBlacklist) {            //If there is a blacklist symbol in the url, report an exception
//            if (encodedUrlContains(request, forbidden)) {
//                throw new RequestRejectedException("The request was rejected because the URL contained a potentially malicious String \"" + forbidden + "\"");
//            }
//        }
//        for (String forbidden : this.decodedUrlBlacklist) {
//            if (decodedUrlContains(request, forbidden)) {
//                throw new RequestRejectedException("The request was rejected because the URL contained a potentially malicious String \"" + forbidden + "\"");
//            }
//        }
//    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        firewall.setAllowUrlEncodedDoubleSlash(true);
        return firewall;
    }

    @Override
    public void configure(WebSecurity web) {
        web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
    }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();

            http.authorizeRequests()

                    .antMatchers("/pet","/pet/create","/pet/medInfo/edit","/pet/delete","/pet/detail","/owner_profile","register_owner").authenticated()
                    .anyRequest().permitAll()
                    .and()
                    .formLogin()
                    .usernameParameter("email")
                    .defaultSuccessUrl("/pet")
                    .permitAll()
                    .and()
                    .rememberMe().key("uniqueAndSecret")
                    .and()
                    .logout().logoutSuccessUrl("/").permitAll();
        }


    }

