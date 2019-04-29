package com.ichuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.ichuang.pojo.Teacher;
import com.ichuang.service.AccountService;
import com.ichuang.service.TeacherService;
import com.ichuang.utils.Page;
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
    /**
     * 修改教师信息
     */
    @ResponseBody
    @RequestMapping("/updateTeacher.action")
    public String updateMember(@RequestBody Teacher teacher){
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
    public String addMember(@RequestBody Teacher teacher){
        if (teacherService.getById(teacher.getId())!=null){
            return "该社员已存在！";
        }
        //受影响的行数
        int rows = teacherService.add(teacher);
        if (rows > 0){
            return "SUCCESS";
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 删除教师信息
     */
    @ResponseBody
    @RequestMapping("/deleteMemberTeacher.action")
    public String deleteMember(@RequestBody Teacher teacher){
        int rows = teacherService.delete(teacher.getId());
        accountService.deleteAccount(teacher.getId());
        if (rows > 0){
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
    public String uploadMemberPhoto(@RequestParam("file") MultipartFile multipartFile , HttpServletRequest httpServletRequest) throws IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        String id = httpServletRequest.getParameter("id");
        Teacher teacher = teacherService.getById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Teacher",JSONObject.toJSON(teacher));
        //设置上传头像的保存地址目录
        String dirPath = "C:/tomcat/apache-tomcat-9.0.12/webapps/images/teacher/";
        File file = new File(dirPath);
        //如果保存文件的地址不存在，就先创建目录
        if (!file.exists()){
            file.mkdirs();
        }
        //重新命名上传文件（教师号）
        String newFile = dirPath+id+".jpg";

        try {
            //删除原来的图片
            File file1= new File(newFile);
            if (file1.exists()) {
                file1.delete();
            }
            //使用MultipartFile接口方法完成文件上传到指定位置
            multipartFile.transferTo(file1);
            if (teacher.getPhoto()==null){
                teacher.setPhoto("https://www.iwchuang.cn/images/teacher/"+id+".jpg");
                teacherService.update(teacher);
            }
        }catch (Exception e){
            e.printStackTrace();
            return jsonObject.toJSONString();
        }
        jsonObject.put("Teacher",JSONObject.toJSON(teacher));
        return jsonObject.toJSONString();
    }
}
