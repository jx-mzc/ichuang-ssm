package com.ichuang.dao;

import com.ichuang.pojo.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 账户DAO层接口
 */
public interface AccountDao {
    //添加账户
    public int addAccount(Account account);
    //删除账户
    public int deleteAccount(@Param("account") String id);
    //更新账户信息
    public int updateAccount(Account account);
    //查询账户信息
    public Account getAccountById(@Param("account") String id);
    //通过账户和密码查询用户
    public Account findAccount(@Param("account") String id,@Param("password") String password);
    //查询所有账户信息
    public List<Account> listAccount();
}
