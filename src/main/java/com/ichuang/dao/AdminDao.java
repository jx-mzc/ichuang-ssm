package com.ichuang.dao;

import com.ichuang.pojo.Admin;

import java.util.List;

/**
 * 管理员dao层接口
 */
public interface AdminDao {
    //添加管理员
    public int add(Admin admin);
    //删除管理员
    public int delete( String id);
    //更新管理员信息
    public int update(Admin admin);
    //查询管理员信息
    public Admin getById( String id);
    //查询所有管理员信息
    public List<Admin> listAll(Admin admin);
}
