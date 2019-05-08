package com.ichuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.ichuang.pojo.PointDetail;
import com.ichuang.service.PointDetailService;
import com.ichuang.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * 积分明细控制器类
 */
@Controller
public class PointDetailController {
    //注入PointDetailService
    @Autowired
    private PointDetailService pointDetailService;
    /**
     * 查找积分明细信息
     */
    @ResponseBody
    @RequestMapping("/getPointDetail.action")
    public String getPointDetail(@RequestBody Integer id ){
        PointDetail pointDetail = pointDetailService.getById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("PointDetail",JSONObject.toJSON(pointDetail));
        return jsonObject.toJSONString();
    }
    /**
     * 查询积分明细信息列表
     */
    @ResponseBody
    @RequestMapping("/listPointDetail.action")
    public String listPointDetail(@RequestParam(defaultValue="1", required=false)Integer page,
                            @RequestParam(defaultValue="10",required=false)Integer rows,
                                  Integer id, String point_id, String point_name, String time, Integer count){
        Page<PointDetail> pointDetailPage= pointDetailService.listAll(page,rows,id,point_id,point_name,time,count);
        return JSONObject.toJSON(pointDetailPage).toString();
    }
    /**
     * 修改积分明细信息
     */
    @ResponseBody
    @RequestMapping("/updatePointDetail.action")
    public String updatePointDetail(@RequestBody PointDetail pointDetail){
        int rows = pointDetailService.update(pointDetail);
        PointDetail pointDetail1 = pointDetailService.getById(pointDetail.getId());
        if (rows >0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("PointDetail",JSONObject.toJSON(pointDetail1));
            return jsonObject.toJSONString();
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 添加积分明细信息
     */
    @ResponseBody
    @RequestMapping("/addPointDetail.action")
    public String addPointDetail(@RequestBody PointDetail pointDetail){
        if (pointDetailService.getById(pointDetail.getId())!=null){
            return "该积分明细已存在！";
        }
        //受影响的行数
        int rows = pointDetailService.add(pointDetail);
        if (rows > 0){
            return "SUCCESS";
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 删除积分明细信息
     */
    @ResponseBody
    @RequestMapping("/deletePointDetail.action")
    public String deletePointDetail(Integer id){
        int rows = pointDetailService.delete(id);
        if (rows > 0){
            return "SUCCESS";
        }else {
            return "FAIL";
        }
    }
}
