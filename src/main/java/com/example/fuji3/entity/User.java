package com.example.fuji3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/*
* テーブルに格納する値、及びUserの定義
* IDは自動採番、ユーザ名をユニークとする
*/

@Table(name = "login_user")
@Entity
@Data
@NoArgsConstructor //デフォルトコンストラクタの自動生成
@AllArgsConstructor //全メンバへ値をセットする為のコンストラクタを自動生成する
@ToString(exclude = {"mailaddress","password"}) //toStringメソッドをオーバーライドできる

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主キーに一意な値を自動生成する AUTO INCREMENTみたいなもの？
    private Long id; //intはプリミティブ型なのでnullになれない。Longは参照型の為OK
    @Column (name = "user_name",length = 20,nullable = false,unique = true)
    private String name;
    @Column (name="mailaddress",length = 120,nullable = false)
    private String mailaddress;
    @Column (name="password",length = 120,nullable = false)
    private String password;
    @Column (name="roles",length = 20)
    private String roles;
    @Column (name = "delete_flg",nullable = false)
    private Boolean delete;
}
