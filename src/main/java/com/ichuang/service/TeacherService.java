package com.ichuang.service;

import com.ichuang.pojo.Teacher;
import com.ichuang.utils.Page;

/**
 * 教师Service层接口
 */
public interface TeacherService {
    //添加教师
    int add(Teacher teacher);
    //删除教师
    int delete(String id);
    //更新教师信息
    int update(Teacher teacher);
    //查询教师信息通过id
    Teacher getById( String id);
    //查询教师信息列表
    Page<Teacher> listAll(Integer page, Integer rows, String id, String name, String phone);
}
