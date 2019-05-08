package com.ichuang.service;

import com.ichuang.pojo.Project;
import com.ichuang.utils.Page;

/**
 * 创业项目Service层接口
 */
public interface ProjectService {
    //添加创业项目
    int add(Project project);
    //删除创业项目
    int delete(Integer id);
    //更新创业项目信息
    int update(Project project);
    //查询创业项目信息通过id
    Project getById( Integer id);
    //查询满足条件的创业项目信息列表
    Page<Project> listAll(Integer page, Integer rows, Integer id, String name, String start_time,
                          String end_time, String post_name, String post_id, String type, String rank);
}
