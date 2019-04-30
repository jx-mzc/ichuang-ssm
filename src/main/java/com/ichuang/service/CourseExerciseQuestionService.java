package com.ichuang.service;

import com.ichuang.pojo.CourseExerciseQuestion;
import com.ichuang.utils.Page;

/**
 * 课程练习题目Service层接口
 */
public interface CourseExerciseQuestionService {
    //添加课程练习题目
    int add(CourseExerciseQuestion courseExerciseQuestion);
    //删除课程练习题目
    int delete(String id);
    //更新课程练习题目信息
    int update(CourseExerciseQuestion courseExerciseQuestion);
    //查询课程练习题目信息通过id
    CourseExerciseQuestion getById( String id);
    //查询满足条件的课程练习题目信息列表
    Page<CourseExerciseQuestion> listAll(Integer page, Integer rows, String id, int number,int score,String answer,String exercise_id);
}
