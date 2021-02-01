package com.example.fuji3.dao;

import com.example.fuji3.dao.LoginUserDao;
import com.example.fuji3.repository.FujiUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class FujiUserDetailsService implements UserDetailsService {
    private final FujiUserRepository fujiUserRepository;

    public FujiUserDetailsService(FujiUserRepository fujiUserRepository){
        this.fujiUserRepository = fujiUserRepository;
    }

    /*
     * ユーザ名で検索したユーザーのuserエンティティをLoginUserDaoクラスのインスタンスへ変換する
     *
     * 検索するユーザーのユーザ名でユーザ名検索で検出したユーザーのユーザー情報を返却
     * UsernameNotFoundException ユーザ名でユーザーが検索できなかった場合にスロー
     */

    @Transactional(readOnly = true)//メソッドにTransactionalアノテーションを付与することでトランザクションをかける
                                    //readOnlyをtrueにするとレコード操作処理が走った際に例外　更新の無い、取得系につける
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException{
        assert (name != null);
        log.debug("loadUserByUsername(name):[{}]",name);
        return fujiUserRepository.findByName(name)
                .map(LoginUserDao::new)
                .orElseThrow(() ->new UsernameNotFoundException("User not found by user:["+name+"]"));
    }
}
