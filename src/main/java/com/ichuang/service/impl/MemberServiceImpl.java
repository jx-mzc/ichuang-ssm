package com.ichuang.service.impl;

import com.github.pagehelper.PageInfo;
import com.ichuang.dao.MemberDao;
import com.ichuang.pojo.Member;
import com.ichuang.service.MemberService;
import com.ichuang.utils.Page;
import org.apache.commons.lang3.StringUtils;
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
    public Page<Member> listAll(Integer page, Integer rows, String id, String name, String school_name, String club_name) {
        Member member = new Member();
        if (StringUtils.isNoneBlank(id)){
            member.setId(id);
        }
        if (StringUtils.isNoneBlank(name)){
            member.setName(name);
        }
        if (StringUtils.isNoneBlank(school_name)){
            member.setSchool_name(school_name);
        }
        if (StringUtils.isNoneBlank(club_name)){
            member.setClub_name(club_name);
        }
        //符合条件的总数量
        int total = (int) new PageInfo<>(memberDao.listAll(member)).getTotal();
        member.setStart((page-1)* rows);
        member.setRows(rows);
        // 创建Page返回对象
        List<Member> memberList = memberDao.listAll(member);
        Page<Member> result = new Page<>();
        result.setPage(page);
        result.setRows(memberList);
        result.setSize(rows);
        result.setTotal(total);
        return result;
    }
}
