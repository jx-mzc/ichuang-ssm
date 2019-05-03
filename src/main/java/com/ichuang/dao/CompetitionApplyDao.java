package com.ichuang.dao;

import com.ichuang.pojo.CompetitionApply;

import java.util.List;

/**
 * 创业大赛申请dao层接口
 */
public interface CompetitionApplyDao {
    //添加创业大赛申请
    public int add(CompetitionApply competitionApply);
    //删除创业大赛申请
    public int delete(Integer id);
    //更新创业大赛申请信息
    public int update(CompetitionApply competitionApply);
    //查询创业大赛申请信息
    public CompetitionApply getById( Integer id);
    //查询创业大赛申请信息
    public List<CompetitionApply> listAll(CompetitionApply competitionApply);
}
