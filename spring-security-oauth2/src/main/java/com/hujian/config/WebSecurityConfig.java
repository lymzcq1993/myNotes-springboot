package com.hujian.config;

import com.hujian.handle.LoginSuccessHandler;
import com.hujian.userdetail.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author hujian
 * @since 2022-07-12 14:55
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                //权限名称的处理，在传入User时，需要ROLE_前缀，在配置权限信息（上面的hasRole()、hasAnyRole()时，不需要前缀ROLE_）
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/oauth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                //开启表单登入
                .formLogin()
//                //配置登入接口
//                .loginProcessingUrl("/login")
//                .successHandler(loginSuccessHandler)
                .and()
                .csrf().disable();
    }

    /**
     * 定义权限源的获取方式（内存或数据库）
     * 认证过程中，Authentication和UserDetails概念要清楚，Authentication是前台传过来的待验证的数据
     * UserDetails是存储在数据库（或内存）中的权限源数据，校验的过程就是将这两个数据进行对比，若满足校验条件，则校验通过
     * @param auth
     * @throws Exception
     */
//    @Autowired
//    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
//    }
}
