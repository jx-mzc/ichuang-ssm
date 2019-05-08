package com.ichuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.ichuang.pojo.Point;
import com.ichuang.service.PointService;
import com.ichuang.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * 积分控制器类
 */
@Controller
public class PointController {
    //注入PointService
    @Autowired
    private PointService pointService;

    /**
     * 查找积分信息
     */
    @ResponseBody
    @RequestMapping("/getPoint.action")
    public String getPoint(@RequestBody String id ){
        Point point = pointService.getById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Point",JSONObject.toJSON(point));
        return jsonObject.toJSONString();
    }
    /**
     * 查询积分信息列表
     */
    @ResponseBody
    @RequestMapping("/listPoint.action")
    public String listPoint(@RequestParam(defaultValue="1", required=false)Integer page,
                             @RequestParam(defaultValue="10",required=false)Integer rows,
                            String id, String member_id, String member_name, Integer count){
        Page<Point> pointPage= pointService.listAll(page,rows,id,member_id,member_name,count);
        return JSONObject.toJSON(pointPage).toString();
    }
    /**
     * 修改积分信息
     */
    @ResponseBody
    @RequestMapping("/updatePoint.action")
    public String updatePoint(@RequestBody Point point){
        int rows = pointService.update(point);
        Point point1 = pointService.getById(point.getId());
        if (rows >0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Point",JSONObject.toJSON(point1));
            return jsonObject.toJSONString();
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 添加积分信息
     */
    @ResponseBody
    @RequestMapping("/addPoint.action")
    public String addPoint(@RequestBody Point point){
        if (pointService.getById(point.getId())!=null){
            return "该积分已存在！";
        }
        //受影响的行数
        int rows = pointService.add(point);
        if (rows > 0){
            return "SUCCESS";
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 删除积分信息
     */
    @ResponseBody
    @RequestMapping("/deletePoint.action")
    public String deletePoint(String id){
        int rows = pointService.delete(id);
        if (rows > 0){
            return "SUCCESS";
        }else {
            return "FAIL";
        }
    }
}
