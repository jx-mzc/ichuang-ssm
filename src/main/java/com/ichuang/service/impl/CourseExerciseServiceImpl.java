package com.ichuang.service.impl;

import com.github.pagehelper.PageInfo;
import com.ichuang.dao.CourseExerciseDao;
import com.ichuang.pojo.CourseExercise;
import com.ichuang.service.CourseExerciseService;
import com.ichuang.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 课程章节Service接口实现类
 */
@Service("courseExerciseService")
@Transactional
public class CourseExerciseServiceImpl implements CourseExerciseService {
    //依赖注入CourseExerciseDao
    @Autowired
    private CourseExerciseDao courseExerciseDao;
    @Override
    public int add(CourseExercise courseExercise) {
        return this.courseExerciseDao.add(courseExercise);
    }

    @Override
    public int delete(String id) {
        return this.courseExerciseDao.delete(id);
    }

    @Override
    public int update(CourseExercise courseExercise) {
        return this.courseExerciseDao.update(courseExercise);
    }

    @Override
    public CourseExercise getById(String id) {
        return this.courseExerciseDao.getById(id);
    }

    @Override
    public Page<CourseExercise> listAll(Integer page, Integer rows, String id, String name, String course_name, String course_id, String teacher_id) {
        CourseExercise courseExercise = new CourseExercise();
        if (StringUtils.isNoneBlank(id)){
            courseExercise.setId(id);
        }
        if (StringUtils.isNoneBlank(name)){
            courseExercise.setName(name);
        }
        if (StringUtils.isNoneBlank(course_name)){
            courseExercise.setCourse_name(course_name);
        }
        if (StringUtils.isNoneBlank(course_id)){
            courseExercise.setCourse_id(course_id);
        }
        if (StringUtils.isNoneBlank(teacher_id)){
            courseExercise.setTeacher_id(teacher_id);
        }
        //符合条件的总数量
        int total = (int) new PageInfo<>(courseExerciseDao.listAll(courseExercise)).getTotal();
        courseExercise.setStart((page-1)* rows);
        courseExercise.setRows(rows);
        // 创建Page返回对象
        List<CourseExercise> courseExerciseList = courseExerciseDao.listAll(courseExercise);
        Page<CourseExercise> result = new Page<>();
        result.setPage(page);
        result.setRows(courseExerciseList);
        result.setSize(rows);
        result.setTotal(total);
        return result;
    }
}
