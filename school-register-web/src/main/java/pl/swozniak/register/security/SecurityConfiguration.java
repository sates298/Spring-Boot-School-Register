package pl.swozniak.register.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:security.properties")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    public static final String ROLE_TEACHER = "TEACHER";
    public static final String ROLE_PARENT = "PARENT";

    @Value("teacher.name")
    private String teacherUsername;

    @Value("teacher.password")
    private String teacherPassword;

    @Value("parent.name")
    private String parentUsername;

    @Value("parent.password")
    private String parentPassword;

    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    public SecurityConfiguration(RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(teacherUsername)
                .password(encoder().encode(teacherPassword))
                .roles(ROLE_TEACHER, ROLE_PARENT)
                .and()
                .withUser(parentUsername)
                .password(encoder().encode(parentPassword))
                .roles(ROLE_PARENT);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
//                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/student/*/grades/new",
                        "/student/*/grades/add",
                        "/student/*/grades/*/resit"
                ).hasRole(ROLE_TEACHER)
                .antMatchers(
                        "/subject/**",
                        "/teacher/**"
                ).hasRole(ROLE_PARENT)
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin()
//                .successHandler(new SchoolRegisterSimpleUrlAuthenticationSuccessHandler())
//                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .and()
                .logout();
    }
}
