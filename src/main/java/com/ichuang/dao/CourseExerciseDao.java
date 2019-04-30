package com.ichuang.dao;

import com.ichuang.pojo.CourseExercise;

import java.util.List;

/**
 * 课程练习dao层接口
 */
public interface CourseExerciseDao {
    //添加课程练习
    public int add(CourseExercise courseExercise);
    //删除课程练习
    public int delete(String id);
    //更新课程练习信息
    public int update(CourseExercise courseExercise);
    //查询课程练习信息
    public CourseExercise getById( String id);
    //查询课程练习信息
    public List<CourseExercise> listAll(CourseExercise course);
}
