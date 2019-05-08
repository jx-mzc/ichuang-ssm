package com.ichuang.dao;

import com.ichuang.pojo.PointDetail;

import java.util.List;
/**
 * 积分明细dao层接口
 */
public interface PointDetailDao {
    //添加积分明细
    public int add(PointDetail pointDetail);
    //删除积分明细
    public int delete(Integer id);
    //更新积分明细
    public int update(PointDetail pointDetail);
    //查询积分明细通过ID
    public PointDetail getById( Integer id);
    //查询积分明细
    public List<PointDetail> listAll(PointDetail pointDetail);
}
