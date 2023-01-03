package courses.paint.mini.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/users").permitAll()
                .antMatchers(HttpMethod.GET, "/api/courses").permitAll()
                .antMatchers(HttpMethod.GET, "/api/courses/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/paints").permitAll()
                .antMatchers(HttpMethod.GET, "/api/painting-techniques").permitAll()
                .antMatchers(HttpMethod.GET, "/api/modeling-products").permitAll()
                .antMatchers(HttpMethod.GET, "/api/games").permitAll()
                .antMatchers(HttpMethod.GET, "/api/games/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/factions/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/producers").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .cors()
                .and()
                .build();
    }
}
