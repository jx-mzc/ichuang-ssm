package com.ichuang.service;

import com.ichuang.pojo.Competition;
import com.ichuang.utils.Page;

/**
 * 创业大赛Service层接口
 */
public interface CompetitionService {
    //添加创业大赛
    int add(Competition competition);
    //删除创业大赛
    int delete(Integer id);
    //更新创业大赛信息
    int update(Competition competition);
    //查询创业大赛信息通过id
    Competition getById( Integer id);
    //查询满足条件的创业大赛信息列表
    Page<Competition> listAll(Integer page, Integer rows, Integer id, String name, String start_time,
                              String end_time, String post, String hold, String rank);
}
