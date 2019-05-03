package com.ichuang.service.impl;

import com.github.pagehelper.PageInfo;
import com.ichuang.dao.ProjectApplyDao;
import com.ichuang.pojo.ProjectApply;
import com.ichuang.service.ProjectApplyService;
import com.ichuang.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 创业大赛申请Service接口实现类
 */
@Service("projectApplyService")
@Transactional
public class ProjectApplyServiceImpl implements ProjectApplyService {
    //依赖注入ProjectApplyDao
    @Autowired
    private ProjectApplyDao projectApplyDao;

    @Override
    public int add(ProjectApply projectApply) {
        return this.projectApplyDao.add(projectApply);
    }

    @Override
    public int delete(Integer id) {
        return this.projectApplyDao.delete(id);
    }

    @Override
    public int update(ProjectApply projectApply) {
        return this.projectApplyDao.update(projectApply);
    }

    @Override
    public ProjectApply getById(Integer id) {
        return this.projectApplyDao.getById(id);
    }

    @Override
    public Page<ProjectApply> listAll(Integer page, Integer rows, Integer id, String project_name,
                                      Integer project_id, String apply_name, String apply_id, Integer status) {
        ProjectApply projectApply = new ProjectApply();
        if (id!=null){
            projectApply.setId(id);
        }
        if (StringUtils.isNoneBlank(project_name)){
            projectApply.setProject_name(project_name);
        }
        if (project_id!=null){
            projectApply.setProject_id(project_id);
        }
        if (StringUtils.isNoneBlank(apply_name)){
            projectApply.setApply_name(apply_name);
        }
        if (StringUtils.isNoneBlank(apply_id)){
            projectApply.setApply_id(apply_id);
        }
        if (status!=null){
            projectApply.setStatus(status);
        }
        //符合条件的总数量
        int total = (int) new PageInfo<>(projectApplyDao.listAll(projectApply)).getTotal();
        projectApply.setStart((page-1)* rows);
        projectApply.setRows(rows);
        // 创建Page返回对象
        List<ProjectApply> projectApplyList = projectApplyDao.listAll(projectApply);
        Page<ProjectApply> result = new Page<>();
        result.setPage(page);
        result.setRows(projectApplyList);
        result.setSize(rows);
        result.setTotal(total);
        return result;
    }
}
