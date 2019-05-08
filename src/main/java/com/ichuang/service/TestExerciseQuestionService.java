package com.ichuang.service;

import com.ichuang.pojo.TestExerciseQuestion;
import com.ichuang.utils.Page;

public interface TestExerciseQuestionService {
    //添加考核练习
    int add(TestExerciseQuestion testExerciseQuestion);
    //删除考核练习
    int delete(Integer id);
    //更新考核信息
    int update(TestExerciseQuestion testExerciseQuestion);
    //查询考核信息通过id
    TestExerciseQuestion getById( Integer id);
    //查询满足条件的考核信息列表
    Page<TestExerciseQuestion> listAll(Integer page, Integer rows, Integer id, Integer number, Integer score, String answer, Integer assessment_id);
}

