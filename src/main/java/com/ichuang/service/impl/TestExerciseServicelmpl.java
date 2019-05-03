package com.ichuang.service.impl;

import com.github.pagehelper.PageInfo;
import com.ichuang.dao.TestExerciseDao;
import com.ichuang.pojo.TestExercise;
import com.ichuang.service.TestExerciseService;
import com.ichuang.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 课程章节Service接口实现类
 */
@Service("testExerciseService")
@Transactional
public class TestExerciseServicelmpl implements TestExerciseService {
    //依赖注入TestExerciseDao
    @Autowired
    private TestExerciseDao testExerciseDao;
    @Override
    public int add(TestExercise testExercise) {
        return this.testExerciseDao.add(testExercise);
    }

    @Override
    public int delete(Integer id) {
        return this.testExerciseDao.delete(id);
    }

    @Override
    public int update(TestExercise testExercise) {
        return this.testExerciseDao.update(testExercise);
    }

    @Override
    public TestExercise getById(Integer id) {
        return this.testExerciseDao.getById(id);
    }

    @Override
    public Page<TestExercise> listAll(Integer page, Integer rows, Integer id, String name, String post_name, Integer post_id) {
        TestExercise testExercise = new TestExercise();
        if (id!=null){
            testExercise.setId(id);
        }
        if (StringUtils.isNoneBlank(name)){
            testExercise.setName(name);
        }
        if (StringUtils.isNoneBlank(post_name)){
            testExercise.setPost_name(post_name);
        }
        if (post_id!=null){
            testExercise.setPost_id(post_id);
        }
        //符合条件的总数量
        int total = (int) new PageInfo<>(testExerciseDao.listAll(testExercise)).getTotal();
        testExercise.setStart((page-1)* rows);
        testExercise.setRows(rows);
        // 创建Page返回对象
        List<TestExercise> courseExerciseList = testExerciseDao.listAll(testExercise);
        Page<TestExercise> result = new Page<>();
        result.setPage(page);
        result.setRows(courseExerciseList);
        result.setSize(rows);
        result.setTotal(total);
        return result;
    }
}

