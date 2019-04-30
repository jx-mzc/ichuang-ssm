package com.ichuang.service;

import com.ichuang.pojo.Course;
import com.ichuang.utils.Page;

/**
 * 课程Service层接口
 */
public interface CourseService {
    //添加课程
    int add(Course course);
    //删除课程
    int delete(String id);
    //更新课程信息
    int update(Course course);
    //查询课程信息通过id
    Course getById( String id);
    //查询满足条件的课程信息列表
    Page<Course> listAll(Integer page, Integer rows, String id, String name, String teacher_name, String teacher_id);
}
