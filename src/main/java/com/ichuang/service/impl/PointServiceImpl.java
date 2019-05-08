package com.ichuang.service.impl;

import com.github.pagehelper.PageInfo;
import com.ichuang.dao.PointDao;
import com.ichuang.pojo.Point;
import com.ichuang.service.PointService;
import com.ichuang.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 积分Service接口实现类
 */
@Service("pointService")
@Transactional
public class PointServiceImpl implements PointService {
    //注入PointDao
    @Autowired
    private PointDao pointDao;

    @Override
    public int add(Point point) {
        return this.pointDao.add(point);
    }

    @Override
    public int delete(String id) {
        return this.pointDao.delete(id);
    }

    @Override
    public int update(Point point) {
        return this.pointDao.update(point);
    }

    @Override
    public Point getById(String id) {
        return this.pointDao.getById(id);
    }

    @Override
    public Page<Point> listAll(Integer page, Integer rows, String id, String member_id, String member_name, Integer count) {
        Point point = new Point();
        if (StringUtils.isNoneBlank(id)){
            point.setId(id);
        }
        if (StringUtils.isNoneBlank(member_id)){
            point.setMember_id(member_id);
        }
        if (StringUtils.isNoneBlank(member_name)){
            point.setMember_name(member_name);
        }
        if (count!=null){
            point.setCount(count);
        }
        //查看总记录数
        int total = (int) new PageInfo<>(pointDao.listAll(point)).getTotal();
        //当前页
        point.setStart((page-1) * rows);
        //每页数
        point.setRows(rows);
        //查找分页后的数据
        List<Point> pointList = pointDao.listAll(point);
        // 创建Page返回对象
        Page<Point> result = new Page<>();
        result.setPage(page);
        result.setRows(pointList);
        result.setSize(point.getRows());
        result.setTotal(total);
        return result;
    }
}
