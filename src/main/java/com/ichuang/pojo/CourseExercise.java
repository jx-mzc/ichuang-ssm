package com.ichuang.pojo;


import java.io.Serializable;

/**
 * 课程练习持久化类
 */
public class CourseExercise implements Serializable {
    private String id;
    private String question_count;
    private String course_name;
    private String course_id;
    private String name;
    private String teacher_id;
    private Integer start;            // 起始行
    private Integer rows;             // 所取行数

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion_count() {
        return question_count;
    }

    public void setQuestion_count(String question_count) {
        this.question_count = question_count;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
