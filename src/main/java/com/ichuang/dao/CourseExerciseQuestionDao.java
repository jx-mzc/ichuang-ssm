package com.ichuang.dao;

import com.ichuang.pojo.CourseExerciseQuestion;

import java.util.List;


/**
 * 课程练习题目dao层接口
 */
public interface CourseExerciseQuestionDao {
    //添加课程练习题目
    public int add(CourseExerciseQuestion courseExerciseQuestion);
    //删除课程练习题目
    public int delete(String id);
    //更新课程练习题目信息
    public int update(CourseExerciseQuestion courseExerciseQuestion);
    //查询课程练习题目信息
    public CourseExerciseQuestion getById( String id);
    //查询课程练习题目信息
    public List<CourseExerciseQuestion> listAll(CourseExerciseQuestion courseExerciseQuestion);
}
