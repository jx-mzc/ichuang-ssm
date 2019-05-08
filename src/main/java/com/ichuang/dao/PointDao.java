package com.ichuang.dao;

import com.ichuang.pojo.Point;

import java.util.List;

/**
 * 积分dao层接口
 */
public interface PointDao {
    //添加积分
    public int add(Point point);
    //删除积分
    public int delete(String id);
    //更新积分
    public int update(Point point);
    //查询积分通过ID
    public Point getById( String id);
    //查询积分
    public List<Point> listAll(Point point);
}
