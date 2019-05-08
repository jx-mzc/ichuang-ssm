package com.ichuang.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * 积分明细持久化类
 */
@Getter
@Setter
public class PointDetail {
    private Integer id;
    private String point_id;
    private String point_name;
    private String time;
    private Integer count;
    private Integer start;            // 起始行
    private Integer rows;             // 所取行数
}
