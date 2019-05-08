package com.ichuang.service;

import com.ichuang.pojo.ProjectApply;
import com.ichuang.utils.Page;

/**
 * 创业项目申请ervice层接口
 */
public interface ProjectApplyService {
    //添加创业项目申请
    int add(ProjectApply projectApply);
    //删除创业项目申请
    int delete(Integer id);
    //更新创业项目申请信息
    int update(ProjectApply projectApply);
    //查询创业项目申请信息通过id
    ProjectApply getById( Integer id);
    //查询满足条件的创业项目申请信息列表
    Page<ProjectApply> listAll(Integer page, Integer rows, Integer id, String project_name, Integer project_id,
                               String apply_name, String apply_id, Integer status);
}
