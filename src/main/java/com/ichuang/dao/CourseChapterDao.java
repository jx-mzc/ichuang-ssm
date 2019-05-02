package com.ichuang.dao;

import com.ichuang.pojo.CourseChapter;

import java.util.List;

/**
 * 课程章节dao层接口
 */
public interface CourseChapterDao {
    //添加课程章节
    public int add(CourseChapter courseChapter);
    //删除课程章节
    public int delete(Integer id);
    //更新课程章节信息
    public int update(CourseChapter courseChapter);
    //查询课程章节信息
    public CourseChapter getById( Integer id);
    //查询课程章节信息
    public List<CourseChapter> listAll(CourseChapter courseChapter);
}
