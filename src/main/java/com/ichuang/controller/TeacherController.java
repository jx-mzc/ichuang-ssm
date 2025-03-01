package com.ichuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.ichuang.pojo.Account;
import com.ichuang.pojo.Teacher;
import com.ichuang.service.AccountService;
import com.ichuang.service.TeacherService;
import com.ichuang.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

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
    public String listTeacher(@RequestParam(defaultValue="1", required=false)Integer page,
                             @RequestParam(defaultValue="10",required=false)Integer rows,
                             String id,String name,String phone){
        Page<Teacher> teacherPage= teacherService.listAll(page,rows,id,name,phone);
        return JSONObject.toJSON(teacherPage).toString();
    }
    /**
     * 修改教师信息
     */
    @ResponseBody
    @RequestMapping("/updateTeacher.action")
    public String updateTeacher(@RequestBody Teacher teacher){
        int rows = teacherService.update(teacher);
        Teacher teacher1 = teacherService.getById(teacher.getId());
        if (rows >0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Teacher",JSONObject.toJSON(teacher1));
            return jsonObject.toJSONString();
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 添加教师信息
     */
    @ResponseBody
    @RequestMapping("/addTeacher.action")
    public String addTeacher(@RequestBody Teacher teacher){
        if (teacherService.getById(teacher.getId())!=null){
            return "该教师已存在！";
        }
        //受影响的行数
        int rows = teacherService.add(teacher);
        if (rows > 0){
            Account account = new Account();
            account.setAccount(teacher.getId());
            account.setPassword(teacher.getPassword());
            account.setTypes(3);
            int row = accountService.addAccount(account);
            if (row > 0){
                return "SUCCESS";
            }else {
                return "FAIL";
            }
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 删除教师信息
     */
    @ResponseBody
    @RequestMapping("/deleteTeacher.action")
    public String deleteTeacher(String id){
        Teacher teacher = teacherService.getById(id);
        String path = teacher.getPhoto();
        int rows = teacherService.delete(id);
        accountService.deleteAccount(id);
        if (rows > 0){
            //删除照片
            if (StringUtils.isNoneBlank(path)){
                File file;
                String dirPath = "C:/tomcat/apache-tomcat-9.0.12/webapps/images/teacher/";
                String filename = path.substring(path.lastIndexOf("/")+1);
                file = new File(dirPath+filename);
                file.delete();
            }
            //删除账户
            if (accountService.getAccountById(id)!=null){
                int row = accountService.deleteAccount(id);
                if (row > 0){
                    return "SUCCESS";
                }else {
                    return "FAIL";
                }
            }
            return "SUCCESS";
        }else {
            return "FAIL";
        }
    }
    /**
     * 执行上传头像
     */
    @ResponseBody
    @RequestMapping("/uploadTeacherPhoto.action")
    public String uploadTeacherPhoto(@RequestParam("file") MultipartFile multipartFile , HttpServletRequest httpServletRequest) throws IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        String id = httpServletRequest.getParameter("id");
        Teacher teacher = teacherService.getById(id);
        JSONObject jsonObject = new JSONObject();
        String path = teacher.getPhoto();

        //设置上传头像的保存地址目录
        String dirPath = "C:/tomcat/apache-tomcat-9.0.12/webapps/images/teacher/";

        //获取上传文件的原始名称
        String originalFilename = multipartFile.getOriginalFilename();
        //获取上传文件的后缀
        String prefix = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        //重新命名上传文件
        String newName =  UUID.randomUUID()+"."+prefix;

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
            teacher.setPhoto("https://www.iwchuang.cn/images/teacher/"+newName);
            Teacher teacher1 = new Teacher();
            teacher1.setId(teacher.getId());
            teacher1.setPhoto("https://www.iwchuang.cn/images/teacher/"+newName);
            teacherService.update(teacher1);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("Teacher",JSONObject.toJSON(teacher));
            return jsonObject.toJSONString();
        }
        jsonObject.put("Teacher",JSONObject.toJSON(teacher));
        return jsonObject.toJSONString();
    }
}
