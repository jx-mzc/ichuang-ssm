package com.ichuang.dao;

import com.ichuang.pojo.ActivityApply;

import java.util.List;

/**
 * 创业活动申请dao层接口
 */
public interface ActivityApplyDao {
    //添加创业活动申请
    public int add(ActivityApply activityApply);
    //删除创业活动申请
    public int delete(Integer id);
    //更新创业活动申请信息
    public int update(ActivityApply activityApply);
    //查询创业活动申请信息
    public ActivityApply getById( Integer id);
    //查询创业活动申请信息
    public List<ActivityApply> listAll(ActivityApply activityApply);
}
