package com.ichuang.service.impl;

import com.github.pagehelper.PageInfo;
import com.ichuang.dao.ProjectDao;
import com.ichuang.pojo.Project;
import com.ichuang.service.ProjectService;
import com.ichuang.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 创业大赛Service接口实现类
 */
@Service("projectService")
@Transactional
public class ProjectServiceImpl implements ProjectService {
    //依赖注入ProjectDao
    @Autowired
    private ProjectDao projectDao;

    @Override
    public int add(Project project) {
        return this.projectDao.add(project);
    }

    @Override
    public int delete(Integer id) {
        return this.projectDao.delete(id);
    }

    @Override
    public int update(Project project) {
        return this.projectDao.update(project);
    }

    @Override
    public Project getById(Integer id) {
        return this.projectDao.getById(id);
    }

    @Override
    public Page<Project> listAll(Integer page, Integer rows, Integer id, String name, String start_time,
                                 String end_time, String post_name, String post_id, String type, String rank) {
        Project project = new Project();
        if (id!=null){
            project.setId(id);
        }
        if (StringUtils.isNoneBlank(name)){
            project.setName(name);
        }
        if (StringUtils.isNoneBlank(start_time)){
            project.setStart_time(start_time);
        }
        if (StringUtils.isNoneBlank(end_time)){
            project.setEnd_time(end_time);
        }
        if (StringUtils.isNoneBlank(post_name)){
            project.setPost_name(post_name);
        }
        if (StringUtils.isNoneBlank(post_id)){
            project.setPost_id(post_id);
        }
        if (StringUtils.isNoneBlank(type)){
            project.setType(type);
        }
        if (StringUtils.isNoneBlank(rank)){
            project.setRank(rank);
        }
        //符合条件的总数量
        int total = (int) new PageInfo<>(projectDao.listAll(project)).getTotal();
        project.setStart((page-1)* rows);
        project.setRows(rows);
        // 创建Page返回对象
        List<Project> projectList = projectDao.listAll(project);
        Page<Project> result = new Page<>();
        result.setPage(page);
        result.setRows(projectList);
        result.setSize(rows);
        result.setTotal(total);
        return result;
    }
}
