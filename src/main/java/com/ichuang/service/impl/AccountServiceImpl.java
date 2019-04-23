package com.ichuang.service.impl;

import com.ichuang.dao.AccountDao;
import com.ichuang.pojo.Account;
import com.ichuang.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 账户Service接口实现类
 */
@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService {

    //注入AccountDao
    @Autowired
    private AccountDao accountDao;

    @Override
    public int addAccount(Account account) {
        return this.accountDao.addAccount(account);
    }

    @Override
    public int deleteAccount(String id) {
        return this.accountDao.deleteAccount(id);
    }

    @Override
    public int updateAccount(Account account) {
        return this.accountDao.updateAccount(account);
    }

    @Override
    public Account getAccountById(String id) {
        return this.accountDao.getAccountById(id);
    }

    @Override
    public Account findAccount(String id, String password) {
        return this.accountDao.findAccount(id,password);
    }

    @Override
    public List<Account> listAccount() {
        return this.accountDao.listAccount();
    }
}
