package com.ichuang.dao;

import com.ichuang.pojo.Member;

import java.util.List;

/**
 * 社团成员dao层接口
 */
public interface MemberDao {
    //添加社员
    public int add(Member member);
    //删除社员
    public int delete(String id);
    //更新社员信息
    public int update(Member member);
    //查询社员信息
    public Member getById( String id);
    //查询所有社员信息
    public List<Member> listAll(Member member);
}
