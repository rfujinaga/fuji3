package com.example.fuji3.dao;

import com.example.fuji3.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j //ロギング
public class LoginUserDao extends org.springframework.security.core.userdetails.User { //同名のUserクラスなのでorgから記述
    private User user;

    //DBのUserエンティティでSpringSecurityのユーザ認証のインスタンスを生成する
    public LoginUserDao(com.example.fuji3.entity.User user){
        super(user.getName(), user.getPassword(), user.getDelete(),true,true,true,
                convertGrantedAuthorities(user.getRoles()));
        this.user = user;
    }

    public User getUser(){
        return user;
    }
    /*
    *カンマ区切りの権限をパラメーターとして渡し、
    * SimpleGrantedAuthorityのコレクションを返却してもらう
    */
    static Set<GrantedAuthority> convertGrantedAuthorities(String roles){
        if(roles == null || roles.isEmpty()){
            return Collections.emptySet();
        }
        Set<GrantedAuthority> authorities = Stream.of(roles.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
        return authorities;
    }
}
