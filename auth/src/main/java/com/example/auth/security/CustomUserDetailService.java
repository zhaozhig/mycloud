package com.example.auth.security;

import com.alibaba.fastjson.JSON;

import com.example.auth.model.AccountDetail;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {


    /**
     * 根据用户名获取登录用户信息
     *
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {


        AccountDetail accountDetail = new AccountDetail();
        accountDetail.setName("user");
        accountDetail.setAccount("user");

        String detailJson = JSON.toJSONString(accountDetail);

        String username = "user";
        String password = "user";
        Boolean enable = true;

        Set<SimpleGrantedAuthority> grantedAuthoritySet = new HashSet<>();
        CustomUserDetails userDetails = new CustomUserDetails(username, new BCryptPasswordEncoder().encode(password), enable, true, true, true, grantedAuthoritySet);
        userDetails.setAccountDetail(detailJson);
        return userDetails;
    }
}