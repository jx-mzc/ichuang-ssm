package com.ichuang.service.impl;

import com.github.pagehelper.PageInfo;
import com.ichuang.dao.AdminDao;
import com.ichuang.pojo.Admin;
import com.ichuang.service.AdminService;
import com.ichuang.utils.Page;
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
    public Page<Admin> listAll(Admin admin) {
        int total = (int) new PageInfo<>(adminDao.listAll(admin)).getTotal();
        admin.setStart((admin.getPage()-1) * admin.getRows());
        // 创建Page返回对象
        List<Admin> adminList = adminDao.listAll(admin);
        Page<Admin> result = new Page<>();
        result.setPage(admin.getPage());
        result.setRows(adminList);
        result.setSize(admin.getRows());
        result.setTotal(total);
        return result;
    }

}
