package com.ichuang.dao;

import com.ichuang.pojo.TestExercise;

import java.util.List;

/**
 * 考核dao接口
 */
public interface TestExerciseDao {
    //添加考核练习
    public int add(TestExercise testExercise);
    //删除考核练习
    public int delete(Integer id);
    //更新考核练习信息
    public int update(TestExercise testExercise);
    //查询考核练习信息
    public TestExercise getById( Integer id);
    //查询考核练习信息
    public List<TestExercise> listAll(TestExercise testExercise);
}
