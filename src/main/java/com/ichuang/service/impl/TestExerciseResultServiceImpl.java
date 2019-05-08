package com.ichuang.service.impl;

import com.github.pagehelper.PageInfo;
import com.ichuang.dao.TestExerciseResultDao;
import com.ichuang.pojo.TestExerciseResult;
import com.ichuang.service.TestExerciseResultService;
import com.ichuang.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 课程章节Service接口实现类
 */
@Service("testExerciseResultService")
@Transactional
public class TestExerciseResultServiceImpl implements TestExerciseResultService {
    //依赖注入CourseExerciseResultDao
    @Autowired
    private TestExerciseResultDao testExerciseResultDao;


    @Override
    public int add(TestExerciseResult testExerciseResult) {
        return this.testExerciseResultDao.add(testExerciseResult);
    }

    @Override
    public int delete(Integer id) {
        return this.testExerciseResultDao.delete(id);
    }

    @Override
    public int update(TestExerciseResult testExerciseResult) {
        return this.testExerciseResultDao.update(testExerciseResult);
    }


    @Override
    public TestExerciseResult getById(Integer id) {
        return this.testExerciseResultDao.getById(id);
    }

    @Override
    public Page<TestExerciseResult> listAll(Integer page, Integer rows, Integer id, String assessment_name, Integer assessment_id,
                                            String member_name, String member_id, Integer score) {
        TestExerciseResult testExerciseResult = new TestExerciseResult();
        if (id!=null){
            testExerciseResult.setId(id);
        }
        if (StringUtils.isNoneBlank(assessment_name)){
            testExerciseResult.setAssessment_name(assessment_name);
        }
        if (StringUtils.isNoneBlank(member_name)){
            testExerciseResult.setMember_name(member_name);
        }
        if (assessment_id!=null){
            testExerciseResult.setAssessment_id(assessment_id);
        }
        if (StringUtils.isNoneBlank(member_id)){
            testExerciseResult.setMember_id(member_id);
        }
        if (score!=null){
            testExerciseResult.setScore(score);
        }

        //符合条件的总数量
        int total = (int) new PageInfo<>(testExerciseResultDao.listAll(testExerciseResult)).getTotal();
        testExerciseResult.setStart((page-1)* rows);
        testExerciseResult.setRows(rows);
        // 创建Page返回对象
        List<TestExerciseResult> testExerciseResultList = testExerciseResultDao.listAll(testExerciseResult);
        Page<TestExerciseResult> result = new Page<>();
        result.setPage(page);
        result.setRows(testExerciseResultList);
        result.setSize(rows);
        result.setTotal(total);
        return result;
    }
}

