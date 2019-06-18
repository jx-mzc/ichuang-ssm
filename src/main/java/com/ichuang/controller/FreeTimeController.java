package com.ichuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.ichuang.pojo.FreeTime;
import com.ichuang.service.FreeTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 空闲时间控制器类
 */
@Controller
public class FreeTimeController {
    //注入
    @Autowired
    private FreeTimeService freeTimeService;

    /**
     * 添加空闲时间信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/addFreeTime.action")
    public String addFreeTime(@RequestBody FreeTime freeTime){
        //受影响的行数
        int rows = freeTimeService.addFreeTime(freeTime);
        if (rows > 0){
            return "SUCCESS";
        }
        else {
            return "FAIL";
        }

    }

    /**
     * 根据member_id判断空闲时间信息后
     * @return
     */
    @ResponseBody
    @RequestMapping("/getFreeTime.action")
    public String getFreeTime(@RequestBody FreeTime freeTime,HttpSession httpSession){
        //受影响的行数
        FreeTime freeTime1 = freeTimeService.getFreeTime(freeTime.getMember_id());
        if (freeTime1!=null){
            freeTime = freeTimeService.getFreeTime(freeTime.getMember_id());
            JSONObject jsonObject  = new JSONObject();
            jsonObject.put("FreeTime",JSONObject.toJSON(freeTime));
            return jsonObject.toJSONString();//存在
        }
        else {
            return "FAIL";//不存在
        }

    }
}