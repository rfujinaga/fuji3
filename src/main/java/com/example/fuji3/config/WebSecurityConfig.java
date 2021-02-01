package com.example.fuji3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
/*
*セキュリティのコンフィグクラス
* "/"をトップページとし、誰でも認証可能とする。
* USERとADMINの権限のアクセス可能ページをそれぞれ定義
*/

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //アカウント登録時のパスワードをエンコードする
    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.debug(false)
                .ignoring()
                .antMatchers(
                        "/images/**",
                        "/js/**",
                        "/css/**",
                        "/h2-console/**");//指定されたURLは認証の対象から除外される
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .mvcMatchers("/").permitAll()//("/"は誰でもアクセス可能)
                .mvcMatchers("/members/user/**").hasRole("USER") //USERユーザーがアクセス可能
                .mvcMatchers("/members/admin/**","/signup").hasRole("ADMIN")//ADMINユーザがアクセス可能
                .anyRequest().authenticated()//全てのURLリクエストは認証されているユーザーしかアクセスできない
            .and()
                .formLogin()
                .defaultSuccessUrl("/")//ログイン成功後のリダイレクト先ページのURL
            .and()
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/");//ログイン成功後のリダイレクト先ページのURL
    }



}
