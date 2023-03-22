package com.lot.ccsmsb.security;

import com.lot.ccsmsb.security.handler.CustomAuthenticationEntryPoint;
import com.lot.ccsmsb.security.handler.MyAuthenticationFailureHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.UrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import java.io.PrintWriter;

/**
 * @author ll
 */
@Configuration
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    CustomSecurityMetadataSource customSecurityMetadataSource;

    @Autowired
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/image/**", "/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // // 将URL权限数据源替换为自定义的数据源
        ApplicationContext context = http.getSharedObject(ApplicationContext.class);
        http.apply(new UrlAuthorizationConfigurer<>(context)).withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O object) {

                object.setRejectPublicInvocations(false); // 这个为true, 则所有URL必须具备对应的权限才可以访问，即数据库没有配置该URL时就不能访问
                object.setSecurityMetadataSource(customSecurityMetadataSource);
                return object;
            }
        });


        // http请求访问配置
        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/index.html")
                .loginProcessingUrl("/login")
                // .successForwardUrl("/hello") // 该方式配置时, hello对应的http method应该与登录一致， 登录一般为POST, 所以这个应该用@PostMapping
                .defaultSuccessUrl("/hello") // 该方式配置时， 这个默认是GET方法， 所以/hello应该用@GetMapping注解
                .failureHandler(myAuthenticationFailureHandler)
                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .and()
                .rememberMe().rememberMeServices(new TokenBasedRememberMeServices("remember-me-salt", userDetailsService))
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies();

    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("user"));
    }
}
