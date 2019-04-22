package com.ichuang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ichuang.pojo.Admin;
import com.ichuang.service.AdminService;
import com.ichuang.utiles.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 管理员控制器类
 */
@Controller
public class AdminController {
    //注入AdminService
    @Autowired
    private AdminService adminService;

    /**
     * 查找管理员信息
     */
    @ResponseBody
    @RequestMapping("/getAdmin.action")
    public String getAdminById(@RequestBody Map<String,String> id){
        Admin admin = adminService.getById(id.get("id"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Admin",JSONObject.toJSON(admin));
        return jsonObject.toJSONString();
    }
    /**
     * 向管理管理员信息界面跳转
     */
    @RequestMapping("/toAdmin.action")
    public String toAdmin(){
        return "admin";
    }
    /**
     * 查找所有管理员信息
     */
    @ResponseBody
    @RequestMapping("/listAdmin.action")
    public String listAdmin(Page page){
        PageHelper.offsetPage(page.getStart(),10);
        List<Admin> adminList = new ArrayList<>();
       adminList = adminService.listAll();
       int total = (int)new PageInfo<>(adminList).getTotal();
       page.caculateLast(total);
       return JSONObject.toJSON(adminList).toString();
    }
}
