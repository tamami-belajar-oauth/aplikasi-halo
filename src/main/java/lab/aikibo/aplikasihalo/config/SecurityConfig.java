package lab.aikibo.aplikasihalo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;


/**
 * Created by tamami on 31/08/17.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /* dipake untuk hardcoded, ga dipake saat user pass ada di db
        auth.inMemoryAuthentication()
                .withUser("tamami")
                .password("rahasia")
                .roles("USER");
                 */
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery("select username, password, active as enabled from users where username=?")
            .authoritiesByUsernameQuery("select users.username, perm.user_role from users inner join permission perm " +
                "on users.id = perm.id_user where users.username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/bower_components/**").permitAll()
                .antMatchers("/style/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/halo")
                .and()
                .logout();
    }



}
