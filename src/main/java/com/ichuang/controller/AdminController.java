package com.ichuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.ichuang.pojo.Admin;
import com.ichuang.service.AdminService;
import com.ichuang.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

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
    public String listAdmin(@RequestParam(defaultValue="1",required=false)Integer page,
                            @RequestParam(defaultValue="10",required=false)Integer rows,String name,String id){
       Page<Admin> admins = adminService.listAll(page,rows,name,id);
       return JSONObject.toJSON(admins).toString();
    }
    /**
     * 查找所有管理员信息后台管理平台显示
     */
    @RequestMapping("/listAdmins.action")
    public String listAdmins(@RequestParam(defaultValue="1",required=false)Integer page,
                             @RequestParam(defaultValue="10",required=false)Integer rows, String name,String id,Model model){
        Page<Admin> admins = adminService.listAll(page,rows,name,id);
        model.addAttribute("page",admins);
        return "admin";
    }
    /**
     * 执行上传头像
     */
    @ResponseBody
    @RequestMapping("/uploadAdminPhoto.action")
    public String uploadAdminPhoto(@RequestParam("file") MultipartFile multipartFile , HttpServletRequest httpServletRequest) throws IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        String id = httpServletRequest.getParameter("id");
        Admin admin = adminService.getById(id);
        JSONObject jsonObject = new JSONObject();
        String path = admin.getPhoto();
        String dirPath = "C:/tomcat/apache-tomcat-9.0.12/webapps/images/admin/";

        //获取上传文件的原始名称
        String originalFilename = multipartFile.getOriginalFilename();
        //获取上传文件的后缀
        String prefix = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        //重新命名上传文件
        String newName = UUID.randomUUID()+"."+prefix;

        try {
            File file;
            if (StringUtils.isNoneBlank(path)){
                String filename = path.substring(path.lastIndexOf("/")+1);
                file = new File(dirPath+filename);
                file.delete();
                file = new File(dirPath+newName);
            }
            else {
                file = new File(dirPath+newName);
                //如果保存文件的地址不存在，就先创建目录
                file.mkdirs();
            }
            //使用MultipartFile接口方法完成文件上传到指定位置
            multipartFile.transferTo(file);
            admin.setPhoto("https://www.iwchuang.cn/images/admin/"+newName);
            Admin admin1 = new Admin();
            admin1.setId(admin.getId());
            admin1.setPhoto("https://www.iwchuang.cn/images/admin/"+newName);
            adminService.update(admin1);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("Admin",JSONObject.toJSON(admin));
            return jsonObject.toJSONString();
        }
        jsonObject.put("Admin",JSONObject.toJSON(admin));
        return jsonObject.toJSONString();
    }
}
