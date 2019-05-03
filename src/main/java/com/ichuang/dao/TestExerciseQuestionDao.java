package com.ichuang.dao;

import com.ichuang.pojo.TestExerciseQuestion;

import java.util.List;

public interface TestExerciseQuestionDao {
    //添加课程练习题目
    public int add(TestExerciseQuestion testExerciseQuestion);
    //删除课程练习题目
    public int delete(Integer id);
    //更新课程练习题目信息
    public int update(TestExerciseQuestion testExerciseQuestion);
    //查询课程练习题目信息
    public TestExerciseQuestion getById( Integer id);
    //查询课程练习题目信息
    public List<TestExerciseQuestion> listAll(TestExerciseQuestion testExerciseQuestion);
}
