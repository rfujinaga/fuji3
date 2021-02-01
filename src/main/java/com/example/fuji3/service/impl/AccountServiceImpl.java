package com.example.fuji3.service.impl;

import com.example.fuji3.entity.User;
import com.example.fuji3.repository.FujiUserRepository;
import com.example.fuji3.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final FujiUserRepository fujiUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(FujiUserRepository fujiUserRepository,PasswordEncoder passwordEncoder){
        this.fujiUserRepository = fujiUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll(){
        return fujiUserRepository.findAll();
    }

    @Transactional
    @Override
    public void register(String name,String mailaddress, String password, String[] roles){
        if(fujiUserRepository.findByName(name).isPresent()){
            throw new RuntimeException("invalid name or mailaddress");
        }
        String encodedPassword = passwordEncode(password);
        String joinedRoles = joinRoles(roles);
        log.debug("name:{},mailaddress:{},roles:{}",name, mailaddress,joinedRoles);
        User user =new User(null,name,mailaddress,encodedPassword,joinedRoles,Boolean.TRUE);
        fujiUserRepository.saveAndFlush(user);
    }

    private String passwordEncode(String rawPassword){
        return passwordEncoder.encode(rawPassword);
    }

    private String joinRoles(String[] roles){
        if (roles == null || roles.length == 0){
            return "";
        }
        return Stream.of(roles)
                .map(String::trim)
                .map(String::toUpperCase)
                .collect(Collectors.joining(","));
    }
}
