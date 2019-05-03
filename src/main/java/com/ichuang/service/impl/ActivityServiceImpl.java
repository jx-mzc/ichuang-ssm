package com.ichuang.service.impl;

import com.github.pagehelper.PageInfo;
import com.ichuang.dao.ActivityDao;
import com.ichuang.pojo.Activity;
import com.ichuang.service.ActivityService;
import com.ichuang.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 创业活动Service接口实现类
 */
@Service("activityService")
@Transactional
public class ActivityServiceImpl implements ActivityService {
    //依赖注入ActivityDao
    @Autowired
    private ActivityDao activityDao;

    @Override
    public int add(Activity activity) {
        return this.activityDao.add(activity);
    }

    @Override
    public int delete(Integer id) {
        return this.activityDao.delete(id);
    }

    @Override
    public int update(Activity activity) {
        return this.activityDao.update(activity);
    }

    @Override
    public Activity getById(Integer id) {
        return this.activityDao.getById(id);
    }

    @Override
    public Page<Activity> listAll(Integer page, Integer rows, Integer id, String name, String start_time,
                                  String end_time, String post, String hold) {
        Activity activity = new Activity();
        if (id!=null){
            activity.setId(id);
        }
        if (StringUtils.isNoneBlank(name)){
            activity.setName(name);
        }
        if (StringUtils.isNoneBlank(start_time)){
           activity.setStart_time(start_time);
        }
        if (StringUtils.isNoneBlank(end_time)){
            activity.setEnd_time(end_time);
        }
        if (StringUtils.isNoneBlank(post)){
            activity.setPost(post);
        }
        if (StringUtils.isNoneBlank(hold)){
            activity.setHold(hold);
        }
        //符合条件的总数量
        int total = (int) new PageInfo<>(activityDao.listAll(activity)).getTotal();
        activity.setStart((page-1)* rows);
        activity.setRows(rows);
        // 创建Page返回对象
        List<Activity> activityList = activityDao.listAll(activity);
        Page<Activity> result = new Page<>();
        result.setPage(page);
        result.setRows(activityList);
        result.setSize(rows);
        result.setTotal(total);
        return result;
    }
}
