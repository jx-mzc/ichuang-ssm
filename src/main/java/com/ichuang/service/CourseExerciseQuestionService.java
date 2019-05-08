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
    int delete(Integer id);
    //更新课程练习题目信息
    int update(CourseExerciseQuestion courseExerciseQuestion);
    //查询课程练习题目信息通过id
    CourseExerciseQuestion getById( Integer id);
    //查询满足条件的课程练习题目信息列表
    Page<CourseExerciseQuestion> listAll(Integer page, Integer rows, Integer id, Integer number, Integer score, String answer, Integer exercise_id);
}
