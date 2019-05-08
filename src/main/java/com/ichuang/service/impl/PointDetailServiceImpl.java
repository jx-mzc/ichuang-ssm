package com.ichuang.service.impl;

import com.github.pagehelper.PageInfo;
import com.ichuang.dao.PointDetailDao;
import com.ichuang.pojo.PointDetail;
import com.ichuang.service.PointDetailService;
import com.ichuang.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 积分明细Service接口实现类
 */
@Service("pointDetailService")
@Transactional
public class PointDetailServiceImpl implements PointDetailService {
    //注入PointDetailDao
    @Autowired
    private PointDetailDao pointDetailDao;

    @Override
    public int add(PointDetail pointDetail) {
        return this.pointDetailDao.add(pointDetail);
    }

    @Override
    public int delete(Integer id) {
        return this.pointDetailDao.delete(id);
    }

    @Override
    public int update(PointDetail pointDetail) {
        return this.pointDetailDao.update(pointDetail);
    }

    @Override
    public PointDetail getById(Integer id) {
        return this.pointDetailDao.getById(id);
    }

    @Override
    public Page<PointDetail> listAll(Integer page, Integer rows, Integer id, String point_id,
                                     String point_name, String time, Integer count) {
        PointDetail pointDetail = new PointDetail();
        if (id!=null){
            pointDetail.setId(id);
        }
        if (StringUtils.isNoneBlank(point_id)){
            pointDetail.setPoint_id(point_id);
        }
        if (StringUtils.isNoneBlank(point_name)){
            pointDetail.setPoint_name(point_name);
        }
        if (StringUtils.isNoneBlank(time)){
            pointDetail.setTime(time);
        }
        if (count!=null){
            pointDetail.setCount(count);
        }
        //查看总记录数
        int total = (int) new PageInfo<>(pointDetailDao.listAll(pointDetail)).getTotal();
        //当前页
        pointDetail.setStart((page-1) * rows);
        //每页数
        pointDetail.setRows(rows);
        //查找分页后的数据
        List<PointDetail> pointDetailList = pointDetailDao.listAll(pointDetail);
        // 创建Page返回对象
        Page<PointDetail> result = new Page<>();
        result.setPage(page);
        result.setRows(pointDetailList);
        result.setSize(pointDetail.getRows());
        result.setTotal(total);
        return result;
    }
}
