package com.ichuang.service;

import com.ichuang.pojo.Account;

import java.util.List;

/**
 * 用户Service层接口
 */
public interface AccountService {
    //添加账户
    int addAccount(Account account);
    //删除账户
    int deleteAccount( String id);
    //更新账户信息
    int updateAccount(Account account);
    //查询账户信息通过id
    Account getAccountById( String id);
    //通过账户和密码查询用户
    Account findAccount(String id,String password);
    //查询所有账户信息
    List<Account> listAccount();
}
