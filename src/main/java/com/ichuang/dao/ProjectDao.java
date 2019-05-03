package com.ichuang.dao;

import com.ichuang.pojo.Project;

import java.util.List;

/**
 * 创业项目dao层接口
 */
public interface ProjectDao {
    //添加创业项目
    public int add(Project project);
    //删除创业项目
    public int delete(Integer id);
    //更新创业项目信息
    public int update(Project project);
    //查询创业项目信息
    public Project getById( Integer id);
    //查询创业项目信息
    public List<Project> listAll(Project project);
}
