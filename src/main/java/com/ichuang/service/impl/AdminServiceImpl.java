package com.ichuang.service.impl;

import com.ichuang.dao.AdminDao;
import com.ichuang.pojo.Admin;
import com.ichuang.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 账户Service接口实现类
 */
@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

    //注入AccountDao
    @Autowired
    private AdminDao adminDao;
    @Override
    public void add(Admin admin) {
        this.adminDao.add(admin);
    }

    @Override
    public void delete(String id) {
        this.adminDao.delete(id);
    }

    @Override
    public void update(Admin admin) {
        this.adminDao.update(admin);
    }

    @Override
    public Admin getById(String id) {
        return this.adminDao.getById(id);
    }

    @Override
    public List<Admin> listAll() {
        return this.adminDao.listAll();
    }
}
