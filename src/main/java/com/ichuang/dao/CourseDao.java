package com.ichuang.dao;

import com.ichuang.pojo.Course;

import java.util.List;

/**
 * 课程dao层接口
 */
public interface CourseDao {
    //添加课程
    public int add(Course course);
    //删除课程
    public int delete(String id);
    //更新课程信息
    public int update(Course course);
    //查询课程信息
    public Course getById( String id);
    //查询课程信息
    public List<Course> listAll(Course course);
}
