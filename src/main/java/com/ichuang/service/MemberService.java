package com.ichuang.service;

import com.ichuang.pojo.Member;
import com.ichuang.utils.Page;

/**
 * 社员Service层接口
 */
public interface MemberService {
    //添加社员
    int add(Member member);
    //删除社员
    int delete(String id);
    //更新社员信息
    int update(Member member);
    //查询社员信息通过id
    Member getById( String id);
    //查询所有社员信息
    Page<Member> listAll(Integer page, Integer rows, String id, String name, String shcool_name, String club_name);
}
