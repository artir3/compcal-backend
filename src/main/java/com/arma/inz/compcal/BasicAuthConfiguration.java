package com.arma.inz.compcal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class BasicAuthConfiguration
        extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)

                ;
    }

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//
//                .antMatchers("/").permitAll()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/registration").permitAll()
//                .anyRequest()
//                .authenticated()
//                .antMatchers("/user").authenticated()
//                .and().csrf().disable().formLogin()
//                .loginPage("/login")
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .and()
//                .httpBasic();
//    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
//
//    @Autowired
//    UserController userController;
//    private Map<String, String> list = new HashMap<>();

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.getObject();
//
//        auth
//                .inMemoryAuthentication()
//                .withUser("user")
//                .password("{noop}password")
//                .roles("USER");
//    }
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(new AuthenticationProvider() {
//            @Override
//            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//                // Do you database query here
//                ArrayList<GrantedAuthority> authorities = new ArrayList<>();
//                authorities.add(new SimpleGrantedAuthority("ROLE_"));  // list of roles from database
//                return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
//                        authentication.getCredentials(), authorities);
//            }
//
//            @Override
//            public boolean supports(Class<?> authentication) {
//                return true;
//            }
//        })
//    }
//
    @Override
    protected void configure(HttpSecurity http)
            throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/registration").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}