package com.ichuang.service;


import com.ichuang.pojo.TestExerciseResult;
import com.ichuang.utils.Page;

public interface TestExerciseResultService {
    //添加考核
    int add(TestExerciseResult testExerciseResult);
    //删除考核
    int delete(Integer id);
    //更新考核信息
    int update(TestExerciseResult testExerciseResult);
    //查询考核信息通过id
    TestExerciseResult getById( Integer id);
    //查询满足条件的考核信息列表
    Page<TestExerciseResult> listAll(Integer page, Integer rows, Integer id, String assessment_name, Integer assessment_id,
                               String member_name,String member_id,Integer score);
}

