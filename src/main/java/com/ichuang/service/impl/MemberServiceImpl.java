package com.ichuang.service.impl;

import com.ichuang.dao.MemberDao;
import com.ichuang.pojo.Member;
import com.ichuang.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 社员Service接口实现类
 */
@Service("memberService")
@Transactional
public class MemberServiceImpl implements MemberService {

    //注入MemberDao
    @Autowired
    private MemberDao memberDao;
    @Override
    public int add(Member member) {
        return this.memberDao.add(member);
    }

    @Override
    public int delete(String id) {
        return this.memberDao.delete(id);
    }

    @Override
    public int update(Member member) {
        return this.memberDao.update(member);
    }

    @Override
    public Member getById(String id) {
        return this.memberDao.getById(id);
    }

    @Override
    public List<Member> listAll(Member member) {
        return this.memberDao.listAll(member);
    }
}
