package com.ichuang.service.impl;

import com.github.pagehelper.PageInfo;
import com.ichuang.dao.CourseDao;
import com.ichuang.pojo.Course;
import com.ichuang.service.CourseService;
import com.ichuang.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 课程Service接口实现类
 */
@Service("courseService")
@Transactional
public class CourseServiceImpl implements CourseService {
    //依赖注入CourseDao
    @Autowired
    private CourseDao courseDao;

    @Override
    public int add(Course course) {
        return this.courseDao.add(course);
    }

    @Override
    public int delete(Integer id) {
        return this.courseDao.delete(id);
    }

    @Override
    public int update(Course course) {
        return this.courseDao.update(course);
    }

    @Override
    public Course getById(Integer id) {
        return this.courseDao.getById(id);
    }

    @Override
    public Page<Course> listAll(Integer page, Integer rows, Integer id, String name, String teacher_name, String teacher_id) {
        Course course = new Course();
        if (id!=null){
            course.setId(id);
        }
        if (StringUtils.isNoneBlank(name)){
            course.setName(name);
        }
        if (StringUtils.isNoneBlank(teacher_name)){
            course.setTeacher_name(teacher_name);
        }
        if (StringUtils.isNoneBlank(teacher_id)){
            course.setTeacher_id(teacher_id);
        }
        //符合条件的总数量
        int total = (int) new PageInfo<>(courseDao.listAll(course)).getTotal();
        course.setStart((page-1)* rows);
        course.setRows(rows);
        // 创建Page返回对象
        List<Course> courseList = courseDao.listAll(course);
        Page<Course> result = new Page<>();
        result.setPage(page);
        result.setRows(courseList);
        result.setSize(rows);
        result.setTotal(total);
        return result;
    }
}
