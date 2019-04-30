package com.ichuang.service.impl;

import com.github.pagehelper.PageInfo;
import com.ichuang.dao.CourseChapterDao;
import com.ichuang.pojo.CourseChapter;
import com.ichuang.service.CourseChapterService;
import com.ichuang.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 课程章节Service接口实现类
 */
@Service("courseChapterService")
@Transactional
public class CourseChapterServiceImpl implements CourseChapterService {
    //依赖注入CourseChapterDao
    @Autowired
    private CourseChapterDao courseChapterDao;

    @Override
    public int add(CourseChapter courseChapter) {
        return this.courseChapterDao.add(courseChapter);
    }

    @Override
    public int delete(String id) {
        return this.courseChapterDao.delete(id);
    }

    @Override
    public int update(CourseChapter courseChapter) {
        return this.courseChapterDao.update(courseChapter);
    }

    @Override
    public CourseChapter getById(String id) {
        return this.getById(id);
    }

    @Override
    public Page<CourseChapter> listAll(Integer page, Integer rows, String id, String name, String course_name, String course_id) {
        CourseChapter courseChapter = new CourseChapter();
        if (StringUtils.isNoneBlank(id)){
            courseChapter.setId(id);
        }
        if (StringUtils.isNoneBlank(name)){
            courseChapter.setName(name);
        }
        if (StringUtils.isNoneBlank(course_name)){
            courseChapter.setCourse_name(course_name);
        }
        if (StringUtils.isNoneBlank(course_id)){
            courseChapter.setCourse_id(course_id);
        }
        //符合条件的总数量
        int total = (int) new PageInfo<>(courseChapterDao.listAll(courseChapter)).getTotal();
        courseChapter.setStart((page-1)* rows);
        courseChapter.setRows(rows);
        // 创建Page返回对象
        List<CourseChapter> courseChapterList = courseChapterDao.listAll(courseChapter);
        Page<CourseChapter> result = new Page<>();
        result.setPage(page);
        result.setRows(courseChapterList);
        result.setSize(rows);
        result.setTotal(total);
        return result;
    }
}
