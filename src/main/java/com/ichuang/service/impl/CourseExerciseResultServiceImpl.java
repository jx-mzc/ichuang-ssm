package com.ichuang.service.impl;

import com.github.pagehelper.PageInfo;
import com.ichuang.dao.CourseExerciseResultDao;
import com.ichuang.pojo.CourseExerciseResult;
import com.ichuang.service.CourseExerciseResultService;
import com.ichuang.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 课程章节Service接口实现类
 */
@Service("courseExerciseResultService")
@Transactional
public class CourseExerciseResultServiceImpl implements CourseExerciseResultService {
    //依赖注入CourseExerciseResultDao
    @Autowired
    private CourseExerciseResultDao courseExerciseResultDao;

    @Override
    public int add(CourseExerciseResult courseExerciseResult) {
        return this.courseExerciseResultDao.add(courseExerciseResult);
    }

    @Override
    public int delete(Integer id) {
        return this.courseExerciseResultDao.delete(id);
    }

    @Override
    public int update(CourseExerciseResult courseExerciseResult) {
        return this.courseExerciseResultDao.update(courseExerciseResult);
    }

    @Override
    public CourseExerciseResult getById(Integer id) {
        return this.courseExerciseResultDao.getById(id);
    }

    @Override
    public Page<CourseExerciseResult> listAll(Integer page, Integer rows, Integer id, String exercise_name, Integer exercise_id,
                                              String member_name, String member_id, Integer score, Integer point) {
        CourseExerciseResult courseExerciseResult = new CourseExerciseResult();
        if (id!=null){
            courseExerciseResult.setId(id);
        }
        if (StringUtils.isNoneBlank(exercise_name)){
            courseExerciseResult.setExercise_name(exercise_name);
        }
        if (StringUtils.isNoneBlank(member_name)){
            courseExerciseResult.setMember_name(member_name);
        }
        if (exercise_id!=null){
            courseExerciseResult.setExercise_id(exercise_id);
        }
        if (StringUtils.isNoneBlank(member_id)){
            courseExerciseResult.setMember_id(member_id);
        }
        if (score!=null){
            courseExerciseResult.setScore(score);
        }
        if (point!=null){
            courseExerciseResult.setPoint(point);
        }
        //符合条件的总数量
        int total = (int) new PageInfo<>(courseExerciseResultDao.listAll(courseExerciseResult)).getTotal();
        courseExerciseResult.setStart((page-1)* rows);
        courseExerciseResult.setRows(rows);
        // 创建Page返回对象
        List<CourseExerciseResult> courseExerciseResultList = courseExerciseResultDao.listAll(courseExerciseResult);
        Page<CourseExerciseResult> result = new Page<>();
        result.setPage(page);
        result.setRows(courseExerciseResultList);
        result.setSize(rows);
        result.setTotal(total);
        return result;
    }
}
