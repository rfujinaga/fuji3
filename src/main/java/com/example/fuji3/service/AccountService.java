package com.example.fuji3.service;

import com.example.fuji3.entity.User;

import java.util.List;

public interface AccountService {
    List<User> findAll();
    void register(String name,String mailaddress, String password,String[] roles);
}
