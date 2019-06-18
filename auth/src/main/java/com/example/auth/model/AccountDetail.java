package com.example.auth.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountDetail implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 名称
     */
    private String name;

    public AccountDetail() {
    }

    public AccountDetail(Long id, String account, String name) {
        this.id = id;
        this.account = account;
        this.name = name;
    }
}
