package com.example.fuji3.controller;

import com.example.fuji3.dao.LoginUserDao;
import com.example.fuji3.entity.User;
import com.example.fuji3.service.ContentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
/*
*権限ごとに表示する内容のコントローラー
*/
@Controller
@RequestMapping(value = "members")
@Slf4j
public class ContentsController {
    private final ContentsService contentsService;

    public ContentsController(ContentsService contentsService){
        this.contentsService = contentsService;
    }

    @GetMapping(value = "/")
    public String any(Principal principal){ //認証が完了したあと，認証したユーザIDは，Principalオブジェクト（java.security.Principal）としてSubjectに格納
        Authentication authentication = (Authentication) principal; //掴んでいるセッションのPrincipalの詳細情報をSecurityContextHolderに渡すためのオブジェクトAuthentication
        LoginUserDao loginUser = (LoginUserDao) authentication.getPrincipal();
        log.info("#any id:{},name:{}",loginUser.getUser().getId(),loginUser.getUser().getName());
        contentsService.doService();
        return "members/index";
    }

    @GetMapping(value = "user")
    public String user(@AuthenticationPrincipal LoginUserDao loginUser){
        log.info("#user id:{}, name:{}",loginUser.getUser().getId(),loginUser.getUser().getName());
        return "members/admin/index";
    }

    @GetMapping(value = "admin")
    public String admin(@AuthenticationPrincipal(expression = "user") User user){
        log.info("#admin id:{},name:{}",user.getId(),user.getName());
        return "members/admin/index";
    }
}