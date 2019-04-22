package com.ichuang.service;

import com.ichuang.pojo.Admin;

import java.util.List;

/**
 * 管理员Service层接口
 */
public interface AdminService {
    //添加管理员
    void add(Admin admin);
    //删除管理员
    void delete(String id);
    //更新管理员信息
    void update(Admin admin);
    //查询管理员信息
    Admin getById( String id);
    //查询所有管理员信息
    List<Admin> listAll();
}
