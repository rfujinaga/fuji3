package com.example.fuji3.repository;

import com.example.fuji3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/*
*ユーザ名で検索するメソッドの実装
* <>：ジェネリクス：データ型名をクラスやメソッドに付けることで、
* Integer型やString型などの様々な型に対応するクラスやメソッドを作ることができる
*/

@Repository
public interface FujiUserRepository extends JpaRepository<User,Long> { //JpaRepository<エンティティのクラス名,IDの型>　で紐付け・検索ができる
    //Optional<xxx> nullの可能性があるオブジェクトの型を指定することでExceptionを防止する
    // ユーザ名で検索する
    Optional<User> findByName(String name);
}
