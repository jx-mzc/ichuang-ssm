package com.ichuang.service.impl;

import com.github.pagehelper.PageInfo;
import com.ichuang.dao.TeacherDao;
import com.ichuang.pojo.Teacher;
import com.ichuang.service.TeacherService;
import com.ichuang.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 社员Service接口实现类
 */
@Service("teacherService")
@Transactional
public class TeacherServiceImpl implements TeacherService {

    //依赖注入TeacherDao
    @Autowired
    private TeacherDao teacherDao;

    @Override
    public int add(Teacher teacher) {
        return this.teacherDao.add(teacher);
    }

    @Override
    public int delete(String id) {
        return this.teacherDao.delete(id);
    }

    @Override
    public int update(Teacher teacher) {
        return this.teacherDao.update(teacher);
    }

    @Override
    public Teacher getById(String id) {
        return this.teacherDao.getById(id);
    }

    @Override
    public Page<Teacher> listAll(Integer page, Integer rows, String id, String name, String phone) {
        Teacher teacher = new Teacher();
        if (StringUtils.isNoneBlank(id)){
            teacher.setId(id);
        }
        if (StringUtils.isNoneBlank(name)){
            teacher.setName(name);
        }
        if (StringUtils.isNoneBlank(phone)){
            teacher.setPhone(phone);
        }
        //总条数
        int total = (int) new PageInfo<>(teacherDao.listAll(teacher)).getTotal();
        //设置分页信息
        teacher.setStart((page-1)* rows);
        teacher.setRows(rows);
        // 创建Page返回对象
        List<Teacher> teacherList = teacherDao.listAll(teacher);
        Page<Teacher> result = new Page<>();
        result.setPage(page);
        result.setRows(teacherList);
        result.setSize(rows);
        result.setTotal(total);
        return result;
    }
}
