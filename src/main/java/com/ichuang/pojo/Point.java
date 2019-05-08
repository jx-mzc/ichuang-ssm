package com.ichuang.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 积分持久化类
 */
@Getter
@Setter
public class Point implements Serializable {
    private String id;   //和用户id一致
    private Integer count;
    private String member_id;
    private String member_name;
    private Integer start;            // 起始行
    private Integer rows;             // 所取行数
}
