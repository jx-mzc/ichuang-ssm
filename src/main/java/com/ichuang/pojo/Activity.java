package com.ichuang.pojo;

import java.io.Serializable;

/**
 * 创业活动持久化类
 */
public class Activity implements Serializable {
    private Integer id;
    private String name;
    private String start_time;
    private String end_time;
    private String post;//发布方
    private String hold;//举办方
    private String introduction;
    private String notification_file;//详细通知文件地址
    private String apply_file;//申请表文件地址
    private Integer start;            // 起始行
    private Integer rows;             // 所取行数

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getHold() {
        return hold;
    }

    public void setHold(String hold) {
        this.hold = hold;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getNotification_file() {
        return notification_file;
    }

    public void setNotification_file(String notification_file) {
        this.notification_file = notification_file;
    }

    public String getApply_file() {
        return apply_file;
    }

    public void setApply_file(String apply_file) {
        this.apply_file = apply_file;
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
