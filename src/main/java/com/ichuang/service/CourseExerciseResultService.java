package com.ichuang.service;

import com.ichuang.pojo.CourseExerciseResult;
import com.ichuang.utils.Page;

/**
 * 课程练习结果Service层接口
 */
public interface CourseExerciseResultService {
    //添加课程练习结果
    int add(CourseExerciseResult courseExerciseResult);
    //删除课程练习结果
    int delete(Integer id);
    //更新课程练习结果信息
    int update(CourseExerciseResult courseExerciseResult);
    //查询课程练习结果信息通过id
    CourseExerciseResult getById( Integer id);
    //查询满足条件的课程练习结果信息列表
    Page<CourseExerciseResult> listAll(Integer page, Integer rows, Integer id, String exercise_name, Integer exercise_id,
                                       String member_name,String member_id,Integer score,Integer point);
}
