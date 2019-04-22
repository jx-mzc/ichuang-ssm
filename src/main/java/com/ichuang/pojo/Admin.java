package com.ichuang.pojo;

import java.io.Serializable;

/**
 * 后台管理员持久化类
 */
public class Admin implements Serializable {
    private String id;//管理员号
    private String name;//管理员名
    private String sex;//性别
    private String phone;//电话号码
    private String photo;//照片地址

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
