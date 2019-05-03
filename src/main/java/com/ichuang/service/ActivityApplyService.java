package com.ichuang.service;

import com.ichuang.pojo.ActivityApply;
import com.ichuang.utils.Page;

/**
 * 创业活动申请ervice层接口
 */
public interface ActivityApplyService {
    //添加创业活动申请
    int add(ActivityApply activityApply);
    //删除创业活动申请
    int delete(Integer id);
    //更新创业活动申请信息
    int update(ActivityApply activityApply);
    //查询创业活动申请信息通过id
    ActivityApply getById( Integer id);
    //查询满足条件的创业活动申请信息列表
    Page<ActivityApply> listAll(Integer page, Integer rows, Integer id, String activity_name, Integer activity_id,
                           String apply_name, String apply_id);
}
