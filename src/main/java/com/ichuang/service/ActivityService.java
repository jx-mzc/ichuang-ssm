package com.ichuang.service;

import com.ichuang.pojo.Activity;
import com.ichuang.utils.Page;
/**
 * 创业活动Service层接口
 */
public interface ActivityService {
    //添加创业活动
    int add(Activity activity);
    //删除创业活动
    int delete(Integer id);
    //更新创业活动信息
    int update(Activity activity);
    //查询创业活动信息通过id
    Activity getById( Integer id);
    //查询满足条件的创业活动信息列表
    Page<Activity> listAll(Integer page, Integer rows, Integer id, String name, String start_time,
                           String end_time,String post,String hold);
}
