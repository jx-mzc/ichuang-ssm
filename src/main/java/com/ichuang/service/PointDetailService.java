package com.ichuang.service;

import com.ichuang.pojo.PointDetail;
import com.ichuang.utils.Page;

/**
 * 积分明细Service层接口
 */
public interface PointDetailService {
    //添加积分明细
    int add(PointDetail pointDetail);
    //删除积分明细
    int delete(Integer id);
    //更新积分明细信息
    int update(PointDetail pointDetail);
    //查询积分明细信息通过id
    PointDetail getById( Integer id);
    //查询所有积分明细信息
    Page<PointDetail> listAll(Integer page, Integer rows, Integer id, String point_id, String point_name,
                              String time,Integer count);
}
