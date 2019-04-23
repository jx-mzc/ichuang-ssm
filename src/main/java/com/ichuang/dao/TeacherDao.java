package com.ichuang.dao;

import com.ichuang.pojo.Teacher;

import java.util.List;

/**
 * 指导老师dao层接口
 */
public interface TeacherDao {
    //添加指导老师
    public int add(Teacher teacher);
    //删除指导老师
    public int delete(String id);
    //更新指导老师信息
    public int update(Teacher teacher);
    //查询指导老师信息
    public Teacher getById( String id);
    //查询指导老师信息
    public List<Teacher> listAll(Teacher teacher);
}
