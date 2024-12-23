package com.example.blogsystem.Configuration;

import com.example.blogsystem.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigurationSecurity {

  private final MyUserDetailsService myUserDetailsService;


    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider  daoAuthenticationProvider =new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{

        httpSecurity.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/v1/api/user/register").permitAll()
                .requestMatchers("/v1/api/user/get").hasAuthority("ADMIN")
                .requestMatchers("/v1/api/user/update/{id}"
                        ,"/v1/api/user/delete/{id}","/v1/api/Blog/get"
                        ,"/v1/api/Blog/add","/v1/api/Blog/update/{blogId}" ,
                        "/v1/api/Blog/delete/{blogId}","/v1/api/Blog/get/{blogId}",
                        "/v1/api/Blog/get-by-title/{title}").hasAuthority("USER")
                .anyRequest().authenticated()
                .and().logout().logoutUrl("/v1/api/logout")
                .deleteCookies("JSESSIONID").invalidateHttpSession(true).and()
                .httpBasic();

        return httpSecurity.build();

    }
}
