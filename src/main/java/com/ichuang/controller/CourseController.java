package com.ichuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.ichuang.pojo.Course;
import com.ichuang.service.CourseService;
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
    public String getCourse(Integer id){
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
    public String listCourse(@RequestParam(defaultValue="1", required=false)Integer page,
                             @RequestParam(defaultValue="10",required=false)Integer rows,
                             Integer id, String name, String teacher_name, String teacher_id){
        Page<Course> coursePage= courseService.listAll(page,rows,id,name,teacher_name,teacher_id);
        return JSONObject.toJSON(coursePage).toString();
    }
    /**
     * 修改课程信息
     */
    @ResponseBody
    @RequestMapping("/updateCourse.action")
    public String updateCourse(@RequestBody Course course){
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
    public String addCourse(@RequestBody Course course){
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
    public String deleteCourse(Integer id){
        int rows = courseService.delete(id);
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
    public String uploadCoursePhoto(@RequestParam("file") MultipartFile multipartFile , HttpServletRequest httpServletRequest) throws IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        String id = httpServletRequest.getParameter("id");
        Course course = courseService.getById(Integer.valueOf(id));
        JSONObject jsonObject = new JSONObject();
        String path = course.getPhoto();

        //设置上传头像的保存地址目录
        String dirPath = "C:/tomcat/apache-tomcat-9.0.12/webapps/images/course/";

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
            course.setPhoto("https://www.iwchuang.cn/images/course/"+newName);
            Course course1 = new Course();
            course1.setId(course.getId());
            course1.setPhoto("https://www.iwchuang.cn/images/course/"+newName);
            courseService.update(course1);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("Course",JSONObject.toJSON(course));
            return jsonObject.toJSONString();
        }
        jsonObject.put("Course",JSONObject.toJSON(course));
        return jsonObject.toJSONString();
    }
}
