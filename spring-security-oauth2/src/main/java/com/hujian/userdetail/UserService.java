package com.hujian.userdetail;

import cn.hutool.core.util.StrUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户密码认证
 * @author hujian
 * @since 2022-07-12 15:14
 */
@Component
public class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StrUtil.equals(username,"hujian")){
            Set<GrantedAuthority> roleSet = new HashSet<>();
            roleSet.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new User(username,new BCryptPasswordEncoder().encode("111111"),roleSet);
        }
        else if(StrUtil.equals(username,"jm")){
            Set<GrantedAuthority> roleSet = new HashSet<>();
            roleSet.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new User(username,new BCryptPasswordEncoder().encode("000000"),roleSet);
        }
        return null;
    }
}
