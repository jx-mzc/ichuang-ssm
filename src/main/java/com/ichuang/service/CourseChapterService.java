package com.ichuang.service;


import com.ichuang.pojo.CourseChapter;
import com.ichuang.utils.Page;

/**
 * 课程章节Service层接口
 */
public interface CourseChapterService {
    //添加课程章节
    int add(CourseChapter courseChapter);
    //删除课程章节
    int delete(String id);
    //更新课程章节信息
    int update(CourseChapter courseChapter);
    //查询课程章节信息通过id
    CourseChapter getById( String id);
    //查询满足条件的课程章节信息列表
    Page<CourseChapter> listAll(Integer page, Integer rows, String id, String name, String course_name, String course_id);
}
