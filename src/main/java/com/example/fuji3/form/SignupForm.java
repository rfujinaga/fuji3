package com.example.fuji3.form;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
*アカウント追加に使用するフォームの定義
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupForm {
    @NotNull
    private String mailaddress;
    @NotNull
    private String password;
    @NotNull
    private String name;
}
