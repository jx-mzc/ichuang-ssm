package com.ichuang.service.impl;


import com.ichuang.dao.FreeTimeDao;
import com.ichuang.pojo.FreeTime;
import com.ichuang.service.FreeTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 空闲时间Service接口实现类
 */
@Service("freeTimeService")
@Transactional
public class FreeTimeServiceImpl implements FreeTimeService {
  //注入FreeTimeDao
    @Autowired
    private FreeTimeDao freeTimeDao;

  @Override
  public int addFreeTime(FreeTime freeTime) {
    return this.freeTimeDao.addFreeTime(freeTime);
  }

  @Override
    public String delFreeTime(String member_id) {
        return this.freeTimeDao.delFreeTime(member_id);
    }

  @Override
  public FreeTime getFreeTime(String member_id) {
      return this.freeTimeDao.getFreeTime(member_id);
  }
}
