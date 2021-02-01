package com.example.fuji3.service.impl;

import com.example.fuji3.dao.LoginUserDao;
import com.example.fuji3.service.ContentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContentsServiceImpl implements ContentsService {
    @Override
    public void doService(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        LoginUserDao loginUserDao = (LoginUserDao) authentication.getPrincipal();
        log.info("#doService id:{},name:{}",loginUserDao.getUser().getId(),loginUserDao.getUser().getName());
    }
}
