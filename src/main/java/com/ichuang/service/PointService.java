package com.ichuang.service;

import com.ichuang.pojo.Point;
import com.ichuang.utils.Page;

/**
 * 积分Service层接口
 */
public interface PointService {
    //添加积分
    int add(Point point);
    //删除积分
    int delete(String id);
    //更新积分信息
    int update(Point point);
    //查询积分信息通过id
    Point getById( String id);
    //查询所有积分信息
    Page<Point> listAll(Integer page, Integer rows, String id, String member_id,String member_name,Integer count);
}
