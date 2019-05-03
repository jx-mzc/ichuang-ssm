package com.ichuang.dao;

import com.ichuang.pojo.ProjectApply;

import java.util.List;

/**
 * 创业项目申请dao层接口
 */
public interface ProjectApplyDao {
    //添加创业项目申请
    public int add(ProjectApply projectApply);
    //删除创业项目申请
    public int delete(Integer id);
    //更新创业项目申请信息
    public int update(ProjectApply projectApply);
    //查询创业项目申请信息
    public ProjectApply getById( Integer id);
    //查询创业项目申请信息
    public List<ProjectApply> listAll(ProjectApply projectApply);
}
