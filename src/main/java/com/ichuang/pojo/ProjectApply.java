package com.ichuang.pojo;

import java.io.Serializable;

/**
 * 创业项目申请持久化类
 */
public class ProjectApply implements Serializable {
    private Integer id;
    private String project_name;
    private Integer project_id;
    private String apply_name;
    private String apply_id;
    private Integer status;  //申请状态（）
    private String apply_file;
    private Integer start;            // 起始行
    private Integer rows;             // 所取行数

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public String getApply_name() {
        return apply_name;
    }

    public void setApply_name(String apply_name) {
        this.apply_name = apply_name;
    }

    public String getApply_id() {
        return apply_id;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
