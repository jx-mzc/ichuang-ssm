package com.ichuang.service.impl;

import com.github.pagehelper.PageInfo;
import com.ichuang.dao.CourseExerciseQuestionDao;
import com.ichuang.pojo.CourseExerciseQuestion;
import com.ichuang.service.CourseExerciseQuestionService;
import com.ichuang.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 课程章节Service接口实现类
 */
@Service("courseExerciseQuestionService")
@Transactional
public class CourseExerciseQuestionServiceImpl implements CourseExerciseQuestionService {
    //依赖注入CourseExerciseQuestionDao
    @Autowired
    private CourseExerciseQuestionDao courseExerciseQuestionDao;

    @Override
    public int add(CourseExerciseQuestion courseExerciseQuestion) {
        return this.courseExerciseQuestionDao.add(courseExerciseQuestion);
    }

    @Override
    public int delete(Integer id) {
        return this.courseExerciseQuestionDao.delete(id);
    }

    @Override
    public int update(CourseExerciseQuestion courseExerciseQuestion) {
        return this.courseExerciseQuestionDao.update(courseExerciseQuestion);
    }

    @Override
    public CourseExerciseQuestion getById(Integer id) {
        return this.courseExerciseQuestionDao.getById(id);
    }

    @Override
    public Page<CourseExerciseQuestion> listAll(Integer page, Integer rows, Integer id, Integer number, Integer score,
                                                String answer, Integer exercise_id) {
        CourseExerciseQuestion courseExerciseQuestion = new CourseExerciseQuestion();
        if (id!=null){
            courseExerciseQuestion.setId(id);
        }
        if (number!=null){
            courseExerciseQuestion.setNumber(number);
        }
        if (score!=null){
            courseExerciseQuestion.setScore(score);
        }
        if (StringUtils.isNoneBlank(answer)){
            courseExerciseQuestion.setAnswer(answer);
        }
        if (exercise_id!=null){
            courseExerciseQuestion.setExercise_id(exercise_id);
        }
        //符合条件的总数量
        int total = (int) new PageInfo<>(courseExerciseQuestionDao.listAll(courseExerciseQuestion)).getTotal();
        courseExerciseQuestion.setStart((page-1)* rows);
        courseExerciseQuestion.setRows(rows);
        // 创建Page返回对象
        List<CourseExerciseQuestion> courseExerciseQuestionList = courseExerciseQuestionDao.listAll(courseExerciseQuestion);
        Page<CourseExerciseQuestion> result = new Page<>();
        result.setPage(page);
        result.setRows(courseExerciseQuestionList);
        result.setSize(rows);
        result.setTotal(total);
        return result;
    }
}
