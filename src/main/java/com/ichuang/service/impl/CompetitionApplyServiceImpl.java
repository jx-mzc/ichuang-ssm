package com.ichuang.service.impl;

import com.github.pagehelper.PageInfo;
import com.ichuang.dao.CompetitionApplyDao;
import com.ichuang.pojo.CompetitionApply;
import com.ichuang.service.CompetitionApplyService;
import com.ichuang.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 课程Service接口实现类
 */
@Service("competitionApplyService")
@Transactional
public class CompetitionApplyServiceImpl implements CompetitionApplyService {
    //依赖注入CompetitionApplyDao
    @Autowired
    private CompetitionApplyDao competitionApplyDao;

    @Override
    public int add(CompetitionApply competitionApply) {
        return this.competitionApplyDao.add(competitionApply);
    }

    @Override
    public int delete(Integer id) {
        return this.competitionApplyDao.delete(id);
    }

    @Override
    public int update(CompetitionApply competitionApply) {
        return this.competitionApplyDao.update(competitionApply);
    }

    @Override
    public CompetitionApply getById(Integer id) {
        return this.competitionApplyDao.getById(id);
    }

    @Override
    public Page<CompetitionApply> listAll(Integer page, Integer rows, Integer id, String competition_name,
                                          Integer competition_id, String apply_name, String apply_id, String name,
                                          String member_phone, String type, String progress) {
        CompetitionApply competitionApply = new CompetitionApply();
        if (id!=null){
            competitionApply.setId(id);
        }
        if (StringUtils.isNoneBlank(competition_name)){
            competitionApply.setCompetition_name(competition_name);
        }
        if (competition_id!=null){
            competitionApply.setCompetition_id(competition_id);
        }
        if (StringUtils.isNoneBlank(apply_name)){
            competitionApply.setApply_name(apply_name);
        }
        if (StringUtils.isNoneBlank(apply_id)){
            competitionApply.setApply_id(apply_id);
        }
        if (StringUtils.isNoneBlank(name)){
            competitionApply.setName(name);
        }
        if (StringUtils.isNoneBlank(member_phone)){
            competitionApply.setMember_phone(member_phone);
        }
        if (StringUtils.isNoneBlank(type)){
            competitionApply.setType(type);
        }
        if (StringUtils.isNoneBlank(progress)){
            competitionApply.setProgress(progress);
        }
        //符合条件的总数量
        int total = (int) new PageInfo<>(competitionApplyDao.listAll(competitionApply)).getTotal();
        competitionApply.setStart((page-1)* rows);
        competitionApply.setRows(rows);
        // 创建Page返回对象
        List<CompetitionApply> competitionApplyList = competitionApplyDao.listAll(competitionApply);
        Page<CompetitionApply> result = new Page<>();
        result.setPage(page);
        result.setRows(competitionApplyList);
        result.setSize(rows);
        result.setTotal(total);
        return result;
    }
}
