package com.ichuang.service;

import com.ichuang.pojo.TestExercise;
import com.ichuang.utils.Page;

/**
 * 考核Service层接口
 */
public interface TestExerciseService {
    //添加考核练习
    int add(TestExercise testExercise);
    //删除考核练习
    int delete(Integer id);
    //更新考核信息
    int update(TestExercise testExercise);
    //查询考核信息通过id
    TestExercise getById( Integer id);
    //查询满足条件的考核信息列表
    Page<TestExercise> listAll(Integer page, Integer rows, Integer id, String name, String post_name, Integer post_id);
}
