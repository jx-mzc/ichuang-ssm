package com.ichuang.service.impl;

import com.github.pagehelper.PageInfo;
import com.ichuang.dao.ActivityApplyDao;
import com.ichuang.pojo.ActivityApply;
import com.ichuang.service.ActivityApplyService;
import com.ichuang.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 创业活动申请Service接口实现类
 */
@Service("activityApplyService")
@Transactional
public class ActivityApplyServiceImpl implements ActivityApplyService {
    //依赖注入ActivityApplyDao
    @Autowired
    private ActivityApplyDao activityApplyDao;

    @Override
    public int add(ActivityApply activityApply) {
        return this.activityApplyDao.add(activityApply);
    }

    @Override
    public int delete(Integer id) {
        return this.activityApplyDao.delete(id);
    }

    @Override
    public int update(ActivityApply activityApply) {
        return this.activityApplyDao.update(activityApply);
    }

    @Override
    public ActivityApply getById(Integer id) {
        return this.activityApplyDao.getById(id);
    }

    @Override
    public Page<ActivityApply> listAll(Integer page, Integer rows, Integer id, String activity_name,
                                       Integer activity_id, String apply_name, String apply_id) {
        ActivityApply activityApply = new ActivityApply();
        if (id!=null){
            activityApply.setId(id);
        }
        if (StringUtils.isNoneBlank(activity_name)){
           activityApply.setActivity_name(activity_name);
        }
        if (activity_id!=null){
            activityApply.setActivity_id(activity_id);
        }
        if (StringUtils.isNoneBlank(apply_name)){
            activityApply.setApply_name(apply_name);
        }
        if (StringUtils.isNoneBlank(apply_id)){
            activityApply.setApply_id(apply_id);
        }
        //符合条件的总数量
        int total = (int) new PageInfo<>(activityApplyDao.listAll(activityApply)).getTotal();
        activityApply.setStart((page-1)* rows);
        activityApply.setRows(rows);
        // 创建Page返回对象
        List<ActivityApply> activityApplyList = activityApplyDao.listAll(activityApply);
        Page<ActivityApply> result = new Page<>();
        result.setPage(page);
        result.setRows(activityApplyList);
        result.setSize(rows);
        result.setTotal(total);
        return result;
    }
}
