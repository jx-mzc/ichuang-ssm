package com.ichuang.pojo;

import java.io.Serializable;

/**
 * 账户持久化类
 */
public class Account implements Serializable{
    private String account;//账号
    private String password;//密码
    private int types;//账户类型

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTypes() {
        return types;
    }

    public void setTypes(int types) {
        this.types = types;
    }
}
