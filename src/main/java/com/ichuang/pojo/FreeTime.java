package com.ichuang.pojo;

import java.io.Serializable;

public class FreeTime implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;//空闲号
    private String member_id;//社员学号
    private  String m1;//周一上午
    private  String m2;//周一下午
    private  String m3;//周一晚上
    private  String tu1;//周二上午
    private  String tu2;//周二下午
    private  String tu3;//周二晚上
    private  String w1;//周三上午
    private  String w2;//周三下午
    private  String w3;//周三晚上
    private  String th1;//周四上午
    private  String th2;//周四下午
    private  String th3;//周四晚上
    private  String f1;//周五上午
    private  String f2;//周五下午
    private  String f3;//周五晚上
    private  String sa1;//周六上午
    private  String sa2;//周六下午
    private  String sa3;//周六晚上
    private  String su1;//周日上午
    private  String su2;//周日下午
    private  String su3;//周日晚上
    private Integer start;            // 起始行
    private Integer rows;             // 所取行数

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getM1() {
        return m1;
    }

    public void setM1(String m1) {
        this.m1 = m1;
    }

    public String getM2() {
        return m2;
    }

    public void setM2(String m2) {
        this.m2 = m2;
    }

    public String getM3() {
        return m3;
    }

    public void setM3(String m3) {
        this.m3 = m3;
    }

    public String getTu1() {
        return tu1;
    }

    public void setTu1(String tu1) {
        this.tu1 = tu1;
    }

    public String getTu2() {
        return tu2;
    }

    public void setTu2(String tu2) {
        this.tu2 = tu2;
    }

    public String getTu3() {
        return tu3;
    }

    public void setTu3(String tu3) {
        this.tu3 = tu3;
    }

    public String getW1() {
        return w1;
    }

    public void setW1(String w1) {
        this.w1 = w1;
    }

    public String getW2() {
        return w2;
    }

    public void setW2(String w2) {
        this.w2 = w2;
    }

    public String getW3() {
        return w3;
    }

    public void setW3(String w3) {
        this.w3 = w3;
    }

    public String getTh1() {
        return th1;
    }

    public void setTh1(String th1) {
        this.th1 = th1;
    }

    public String getTh2() {
        return th2;
    }

    public void setTh2(String th2) {
        this.th2 = th2;
    }

    public String getTh3() {
        return th3;
    }

    public void setTh3(String th3) {
        this.th3 = th3;
    }

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    public String getF2() {
        return f2;
    }

    public void setF2(String f2) {
        this.f2 = f2;
    }

    public String getF3() {
        return f3;
    }

    public void setF3(String f3) {
        this.f3 = f3;
    }

    public String getSa1() {
        return sa1;
    }

    public void setSa1(String sa1) {
        this.sa1 = sa1;
    }

    public String getSa2() {
        return sa2;
    }

    public void setSa2(String sa2) {
        this.sa2 = sa2;
    }

    public String getSa3() {
        return sa3;
    }

    public void setSa3(String sa3) {
        this.sa3 = sa3;
    }

    public String getSu1() {
        return su1;
    }

    public void setSu1(String su1) {
        this.su1 = su1;
    }

    public String getSu2() {
        return su2;
    }

    public void setSu2(String su2) {
        this.su2 = su2;
    }

    public String getSu3() {
        return su3;
    }

    public void setSu3(String su3) {
        this.su3 = su3;
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
