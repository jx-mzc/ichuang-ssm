package com.ichuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.ichuang.pojo.Teacher;
import com.ichuang.service.AccountService;
import com.ichuang.service.TeacherService;
import com.ichuang.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 教师控制器类
 */
@Controller
public class TeacherController {
    //依赖注入
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private AccountService accountService;

    /**
     * 通过ID查询教师信息
     */
    @ResponseBody
    @RequestMapping("/getTeacher.action")
    public String getTeacher(String id){
        Teacher teacher = teacherService.getById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Teacher",JSONObject.toJSON(teacher));
        return jsonObject.toJSONString();
    }
    /**
     * 查询教师信息列表
     */
    @ResponseBody
    @RequestMapping("/listTeacher.action")
    public String listMember(@RequestParam(defaultValue="1", required=false)Integer page,
                             @RequestParam(defaultValue="10",required=false)Integer rows,
                             String id,String name,String phone){
        Page<Teacher>  teacherPage= teacherService.listAll(page,rows,id,name,phone);
        return JSONObject.toJSON(teacherPage).toString();
    }
}
