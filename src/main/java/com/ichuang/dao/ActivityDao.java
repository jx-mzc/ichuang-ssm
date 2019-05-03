package com.ichuang.dao;

import com.ichuang.pojo.Activity;

import java.util.List;

/**
 * 创业活动dao层接口
 */
public interface ActivityDao {
    //添加创业活动
    public int add(Activity activity);
    //删除创业活动
    public int delete(Integer id);
    //更新创业活动信息
    public int update(Activity activity);
    //查询创业活动信息
    public Activity getById( Integer id);
    //查询创业活动信息
    public List<Activity> listAll(Activity activity);
}
