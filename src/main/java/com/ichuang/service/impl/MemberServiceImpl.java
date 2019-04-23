package com.ichuang.service.impl;

import com.github.pagehelper.PageInfo;
import com.ichuang.dao.MemberDao;
import com.ichuang.pojo.Member;
import com.ichuang.service.MemberService;
import com.ichuang.utils.Page;
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
    public Page<Member> listAll(Member member) {
        int total = (int) new PageInfo<>(memberDao.listAll(member)).getTotal();
        member.setStart((member.getPage()-1) * member.getRows());
        // 创建Page返回对象
        List<Member> memberList = memberDao.listAll(member);
        Page<Member> result = new Page<>();
        result.setPage(member.getPage());
        result.setRows(memberList);
        result.setSize(member.getRows());
        result.setTotal(total);
        return result;
    }
}
