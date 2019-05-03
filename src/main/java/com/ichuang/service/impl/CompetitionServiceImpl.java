package com.ichuang.service.impl;

import com.github.pagehelper.PageInfo;
import com.ichuang.dao.CompetitionDao;
import com.ichuang.pojo.Competition;
import com.ichuang.service.CompetitionService;
import com.ichuang.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 创业大赛Service接口实现类
 */
@Service("competitionService")
@Transactional
public class CompetitionServiceImpl implements CompetitionService {
    //依赖注入CompetitionDao
    @Autowired
    private CompetitionDao competitionDao;

    @Override
    public int add(Competition competition) {
        return this.competitionDao.add(competition);
    }

    @Override
    public int delete(Integer id) {
        return this.competitionDao.delete(id);
    }

    @Override
    public int update(Competition competition) {
        return this.competitionDao.update(competition);
    }

    @Override
    public Competition getById(Integer id) {
        return this.competitionDao.getById(id);
    }

    @Override
    public Page<Competition> listAll(Integer page, Integer rows, Integer id, String name, String start_time,
                                     String end_time, String post, String hold, String rank) {
        Competition competition = new Competition();
        if (id!=null){
            competition.setId(id);
        }
        if (StringUtils.isNoneBlank(name)){
            competition.setName(name);
        }
        if (StringUtils.isNoneBlank(start_time)){
            competition.setStart_time(start_time);
        }
        if (StringUtils.isNoneBlank(end_time)){
            competition.setEnd_time(end_time);
        }
        if (StringUtils.isNoneBlank(post)){
            competition.setPost(post);
        }
        if (StringUtils.isNoneBlank(hold)){
            competition.setHold(hold);
        }
        if (StringUtils.isNoneBlank(rank)){
            competition.setRank(rank);
        }
        //符合条件的总数量
        int total = (int) new PageInfo<>(competitionDao.listAll(competition)).getTotal();
        competition.setStart((page-1)* rows);
        competition.setRows(rows);
        // 创建Page返回对象
        List<Competition> competitionList = competitionDao.listAll(competition);
        Page<Competition> result = new Page<>();
        result.setPage(page);
        result.setRows(competitionList);
        result.setSize(rows);
        result.setTotal(total);
        return result;
    }
}
