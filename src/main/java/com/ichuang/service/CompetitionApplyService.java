package com.ichuang.service;

import com.ichuang.pojo.CompetitionApply;
import com.ichuang.utils.Page;

/**
 * 创业大赛申请ervice层接口
 */
public interface CompetitionApplyService {
    //添加创业大赛申请
    int add(CompetitionApply competitionApply);
    //删除创业大赛申请
    int delete(Integer id);
    //更新创业大赛申请信息
    int update(CompetitionApply competitionApply);
    //查询创业大赛申请信息通过id
    CompetitionApply getById( Integer id);
    //查询满足条件的创业大赛申请信息列表
    Page<CompetitionApply> listAll(Integer page, Integer rows, Integer id, String competition_name, Integer competition_id,
                                   String apply_name, String apply_id, String name, String member_phone, String type, String progress);
}
