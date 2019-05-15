package com.ichuang.service.impl;

import com.github.pagehelper.PageInfo;
import com.ichuang.dao.AdminDao;
import com.ichuang.pojo.Admin;
import com.ichuang.service.AdminService;
import com.ichuang.utils.Page;
import org.apache.commons.lang3.StringUtils;
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
    public int add(Admin admin) {
        return this.adminDao.add(admin);
    }

    @Override
    public int delete(String id) {
        return this.adminDao.delete(id);
    }

    @Override
    public int update(Admin admin) {
        return this.adminDao.update(admin);
    }

    @Override
    public Admin getById(String id) {
        return this.adminDao.getById(id);
    }

    @Override
    public Page<Admin> listAll(Integer page, Integer rows, String name,String id) {
        //创建管理员对象
        Admin admin = new Admin();
        if (StringUtils.isNoneBlank(name)){
            admin.setName(name);
        }
        if (StringUtils.isNoneBlank(id)){
            admin.setId(id);
        }
        //查看管理员列表总记录数
        int total = (int) new PageInfo<>(adminDao.listAll(admin)).getTotal();
        //当前页
        admin.setStart((page-1) * rows);
        //每页数
        admin.setRows(rows);
        //查找分页后的数据
        List<Admin> adminList = adminDao.listAll(admin);
        // 创建Page返回对象
        Page<Admin> result = new Page<>();
        result.setPage(page);
        result.setRows(adminList);
        result.setSize(admin.getRows());
        result.setTotal(total);
        return result;
    }

}
