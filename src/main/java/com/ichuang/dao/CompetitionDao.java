package com.ichuang.dao;

import com.ichuang.pojo.Competition;

import java.util.List;

/**
 * 创业大赛dao层接口
 */
public interface CompetitionDao {
    //添加创业大赛
    public int add(Competition competition);
    //删除创业大赛
    public int delete(Integer id);
    //更新创业大赛信息
    public int update(Competition competition);
    //查询创业大赛信息
    public Competition getById( Integer id);
    //查询创业大赛信息
    public List<Competition> listAll(Competition competition);
}
