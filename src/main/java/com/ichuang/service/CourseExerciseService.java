package com.ichuang.service;

import com.ichuang.pojo.CourseExercise;
import com.ichuang.utils.Page;

/**
 * 课程练习Service层接口
 */
public interface CourseExerciseService {
    //添加课程练习
    int add(CourseExercise courseExercise);
    //删除课程练习
    int delete(String id);
    //更新课程练习信息
    int update(CourseExercise courseExercise);
    //查询课程练习信息通过id
    CourseExercise getById( String id);
    //查询满足条件的课程练习信息列表
    Page<CourseExercise> listAll(Integer page, Integer rows, String id, String name, String course_name, String course_id,String teacher_id);
}
