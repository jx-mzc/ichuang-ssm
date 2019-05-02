package com.ichuang.dao;

import com.ichuang.pojo.CourseExerciseResult;

import java.util.List;

/**
 * 课程练习结果dao层接口
 */
public interface CourseExerciseResultDao {
    //添加课程练习结果
    public int add(CourseExerciseResult courseExerciseResult);
    //删除课程练习结果
    public int delete(Integer id);
    //更新课程练习结果信息
    public int update(CourseExerciseResult courseExerciseResult);
    //查询课程练习结果信息
    public CourseExerciseResult getById( Integer id);
    //查询课程练习结果信息
    public List<CourseExerciseResult> listAll(CourseExerciseResult courseExerciseResult);
}
