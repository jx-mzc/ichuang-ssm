package com.ichuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.ichuang.pojo.Course;
import com.ichuang.service.CourseService;
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
 * 课程信息控制器类
 */
@Controller
public class CourseController {
    //依赖注入
    @Autowired
    private CourseService courseService;

    /**
     * 通过ID查询课程信息
     */
    @ResponseBody
    @RequestMapping("/getCourse.action")
    public String getTeacher(String id){
        Course course = courseService.getById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Course",JSONObject.toJSON(course));
        return jsonObject.toJSONString();
    }
    /**
     * 查询课程信息列表
     */
    @ResponseBody
    @RequestMapping("/listCourse.action")
    public String listMember(@RequestParam(defaultValue="1", required=false)Integer page,
                             @RequestParam(defaultValue="10",required=false)Integer rows,
                             String id, String name, String teacher_name, String teacher_id){
        Page<Course> coursePage= courseService.listAll(page,rows,id,name,teacher_name,teacher_id);
        return JSONObject.toJSON(coursePage).toString();
    }
    /**
     * 修改课程信息
     */
    @ResponseBody
    @RequestMapping("/updateCourse.action")
    public String updateMember(@RequestBody Course course){
        int rows = courseService.update(course);
        Course course1 = courseService.getById(course.getId());
        if (rows >0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Course",JSONObject.toJSON(course1));
            return jsonObject.toJSONString();
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 添加课程信息
     */
    @ResponseBody
    @RequestMapping("/addCourse.action")
    public String addMember(@RequestBody Course course){
        if (courseService.getById(course.getId())!=null){
            return "该课程已存在！";
        }
        //受影响的行数
        int rows = courseService.add(course);
        if (rows > 0){
            return "SUCCESS";
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 删除课程信息
     */
    @ResponseBody
    @RequestMapping("/deleteCourse.action")
    public String deleteMember(@RequestBody Course course){
        int rows = courseService.delete(course.getId());
        if (rows > 0){
            return "SUCCESS";
        }else {
            return "FAIL";
        }
    }
    /**
     * 执行上传封面照片
     */
    @ResponseBody
    @RequestMapping("/uploadCoursePhoto.action")
    public String uploadMemberPhoto(@RequestParam("file") MultipartFile multipartFile , HttpServletRequest httpServletRequest) throws IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        String id = httpServletRequest.getParameter("id");
        Course course = courseService.getById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Course",JSONObject.toJSON(course));
        //设置上传头像的保存地址目录
        String dirPath = "C:/tomcat/apache-tomcat-9.0.12/webapps/images/course/";
        File file = new File(dirPath);
        //如果保存文件的地址不存在，就先创建目录
        if (!file.exists()){
            file.mkdirs();
        }
        //重新命名上传文件（课程号）
        String newFile = dirPath+id+".jpg";

        try {
            //删除原来的图片
            File file1= new File(newFile);
            if (file1.exists()) {
                file1.delete();
            }
            //使用MultipartFile接口方法完成文件上传到指定位置
            multipartFile.transferTo(file1);
            if (course.getPhoto()==null){
                course.setPhoto("https://www.iwchuang.cn/images/course/"+id+".jpg");
                courseService.update(course);
            }
        }catch (Exception e){
            e.printStackTrace();
            return jsonObject.toJSONString();
        }
        jsonObject.put("Course",JSONObject.toJSON(course));
        return jsonObject.toJSONString();
    }
}
