package com.example.fuji3.controller;

import com.example.fuji3.form.SignupForm;
import com.example.fuji3.service.AccountService;
import com.example.fuji3.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class IndexController {
    private final AccountService accountService;

    public IndexController(AccountService accountService){
        this.accountService = accountService;
    }

    /*
     * トップページ
     *
     * @param signupForm サインアップフォームデータ
     * @param model モデル（ユーザーリスト）
     * @return index
     */

    @GetMapping(value = "/")
    public String index(@ModelAttribute("signup") SignupForm signupForm, Model model){
        List<User> userList = accountService.findAll();
        model.addAttribute("users",userList);
        return "index";
    }

    /*
     * アカウント登録
     *
     * @param signupForm サインアップフォームデータ
     * @param redirectAttributes リダイレクト先へメッセージを送るため
     * @return index (redirect)
     */

    @PostMapping(value = "signup")
    public String signup(@ModelAttribute("signup") SignupForm signupForm, RedirectAttributes redirectAttributes){
        //暫定的に2つの権限を付与
        String[] roles = {"ROLE_USER","ROLE_ADMIN"};
        accountService.register(signupForm.getName(),signupForm.getMailaddress(),signupForm.getPassword(),roles);
        redirectAttributes.addAttribute("successMessage","アカウントの登録が完了しました。");
        return "redirect:/";
    }
}
