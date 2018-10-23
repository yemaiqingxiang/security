package com.wgc.auth.config;

import com.wgc.auth.endpoint.CustomAuthError;
import com.wgc.auth.endpoint.Logout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;

/**
 * 资源服务器配置
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired//自定义错误处理
    private CustomAuthError customAuthError;
    @Autowired //登出控制清空accessToken
    private Logout logout;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(customAuthError)
                .and()
                .authorizeRequests()
                .antMatchers("/test","/oauth/token").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .logout()
                .logoutUrl("/oauth/logout")
                .logoutSuccessHandler(logout);
    }
}
