package com.ichuang.pojo;

import java.io.Serializable;

/**
 * 创业大赛申请持久化类
 */
public class CompetitionApply implements Serializable {
    private Integer id;
    private String competition_name;
    private Integer competition_id;
    private String apply_name;
    private String apply_id;
    private String name;    //参赛项目名
    private String member_phone;
    private String type;    //参赛项目类型
    private String introduction; //参赛项目介绍
    private String progress;    //参赛项目进展
    private String plan_file;   //参赛项目创业计划书文件地址
    private Integer start;            // 起始行
    private Integer rows;             // 所取行数

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompetition_name() {
        return competition_name;
    }

    public void setCompetition_name(String competition_name) {
        this.competition_name = competition_name;
    }

    public Integer getCompetition_id() {
        return competition_id;
    }

    public void setCompetition_id(Integer competition_id) {
        this.competition_id = competition_id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMember_phone() {
        return member_phone;
    }

    public void setMember_phone(String member_phone) {
        this.member_phone = member_phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getPlan_file() {
        return plan_file;
    }

    public void setPlan_file(String plan_file) {
        this.plan_file = plan_file;
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
