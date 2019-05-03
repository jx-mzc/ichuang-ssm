package com.ichuang.dao;

import com.ichuang.pojo.TestExerciseResult;

import java.util.List;

public interface TestExerciseResultDao {
    //添加考核结果
    public int add(TestExerciseResult testExerciseResult);
    //删除考核结果
    public int delete(Integer id);
    //更新考核结果信息
    public int update(TestExerciseResult testExerciseResult);
    //查询考核结果信息
    public TestExerciseResult getById(Integer id);
    //查询课程考核信息
    public List<TestExerciseResult> listAll(TestExerciseResult testExerciseResult);
}
