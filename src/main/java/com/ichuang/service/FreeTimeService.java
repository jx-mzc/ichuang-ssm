package com.ichuang.service;

import com.ichuang.pojo.FreeTime;
import org.apache.ibatis.annotations.Param;

/**
 * 空闲时间表service层接口
 */
public interface FreeTimeService {
    //添加空闲时间信息
    public int addFreeTime(FreeTime freeTime);
    //根据学号删除空闲时间信息
    public String delFreeTime(String member_id);
    //根据学号查询空闲时间
    public  FreeTime getFreeTime(String member_id);

}
