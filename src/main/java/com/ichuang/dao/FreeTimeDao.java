package com.ichuang.dao;

import com.ichuang.pojo.FreeTime;
import org.apache.ibatis.annotations.Param;

/**
 * 空闲时间Dao接口
 */
public interface FreeTimeDao {
    //添加空闲时间信息
    public int addFreeTime(FreeTime freeTime);
    //根据学号删除空闲时间信息
    public String delFreeTime(@Param("member_id") String member_id);
    //根据学号查询
    public FreeTime getFreeTime(@Param("member_id") String member_id);

}
