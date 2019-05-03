package com.ichuang.service.impl;

import com.github.pagehelper.PageInfo;
import com.ichuang.dao.TestExerciseQuestionDao;
import com.ichuang.pojo.TestExerciseQuestion;
import com.ichuang.service.TestExerciseQuestionService;
import com.ichuang.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
     * 课程章节Service接口实现类
     */
    @Service("testExerciseQuestionService")
    @Transactional
    public class TestExerciseQuestionServiceImpl implements TestExerciseQuestionService {
    //依赖注入CourseExerciseQuestionDao
    @Autowired
    private TestExerciseQuestionDao testExerciseQuestionDao;

    @Override
    public int add(TestExerciseQuestion testExerciseQuestion) {
        return this.testExerciseQuestionDao.add(testExerciseQuestion);
    }

    @Override
    public int delete(Integer id) {
        return this.testExerciseQuestionDao.delete(id);
    }

    @Override
    public int update(TestExerciseQuestion testExerciseQuestion) {
        return this.testExerciseQuestionDao.update(testExerciseQuestion);
    }

    @Override
    public TestExerciseQuestion getById(Integer id) {
        return this.testExerciseQuestionDao.getById(id);
    }

    @Override
    public Page<TestExerciseQuestion> listAll(Integer page, Integer rows, Integer id, Integer number, Integer score,
                                              String answer, Integer assessment_id) {
        TestExerciseQuestion testExerciseQuestion = new TestExerciseQuestion();
        if (id != null) {
            testExerciseQuestion.setId(id);
        }
        if (number != null) {
            testExerciseQuestion.setNumber(number);
        }
        if (score != null) {
            testExerciseQuestion.setScore(score);
        }
        if (StringUtils.isNoneBlank(answer)) {
            testExerciseQuestion.setAnswer(answer);
        }
        if (assessment_id != null) {
            testExerciseQuestion.setAssessment_id(assessment_id);
        }
        //符合条件的总数量
        int total = (int) new PageInfo<>(testExerciseQuestionDao.listAll(testExerciseQuestion)).getTotal();
        testExerciseQuestion.setStart((page - 1) * rows);
        testExerciseQuestion.setRows(rows);
        // 创建Page返回对象
        List<TestExerciseQuestion> testExerciseQuestionList = testExerciseQuestionDao.listAll(testExerciseQuestion);
        Page<TestExerciseQuestion> result = new Page<>();
        result.setPage(page);
        result.setRows(testExerciseQuestionList);
        result.setSize(rows);
        result.setTotal(total);
        return result;
    }
}


