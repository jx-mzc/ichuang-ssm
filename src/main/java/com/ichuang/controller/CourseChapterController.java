package com.ichuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.ichuang.pojo.CourseChapter;
import com.ichuang.service.CourseChapterService;
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
 * 课程章节信息控制器类
 */
@Controller
public class CourseChapterController {
    //依赖注入
    @Autowired
    private CourseChapterService courseChapterService;

    /**
     * 通过ID查询课程章节信息
     */
    @ResponseBody
    @RequestMapping("/getCourseChapter.action")
    public String getTeacher(String id){
        CourseChapter courseChapter = courseChapterService.getById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CourseChapter",JSONObject.toJSON(courseChapter));
        return jsonObject.toJSONString();
    }
    /**
     * 查询课程章节信息列表
     */
    @ResponseBody
    @RequestMapping("/listCourseChapter.action")
    public String listMember(@RequestParam(defaultValue="1", required=false)Integer page,
                             @RequestParam(defaultValue="10",required=false)Integer rows,
                             String id, String name, String course_name, String course_id){
        Page<CourseChapter> courseChapterPage= courseChapterService.listAll(page,rows,id,name,course_name,course_id);
        return JSONObject.toJSON(courseChapterPage).toString();
    }
    /**
     * 修改课程章节信息
     */
    @ResponseBody
    @RequestMapping("/updateCourseChapter.action")
    public String updateMember(@RequestBody CourseChapter courseChapter){
        int rows = courseChapterService.update(courseChapter);
        CourseChapter courseChapter1 = courseChapterService.getById(courseChapter.getId());
        if (rows >0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("CourseChapter",JSONObject.toJSON(courseChapter1));
            return jsonObject.toJSONString();
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 添加课程章节信息
     */
    @ResponseBody
    @RequestMapping("/addCourseChapter.action")
    public String addMember(@RequestBody CourseChapter courseChapter){
        if (courseChapterService.getById(courseChapter.getId())!=null){
            return "该课程章节已存在！";
        }
        //受影响的行数
        int rows = courseChapterService.add(courseChapter);
        if (rows > 0){
            return "SUCCESS";
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 删除课程章节信息
     */
    @ResponseBody
    @RequestMapping("/deleteCourseChapter.action")
    public String deleteMember(@RequestBody CourseChapter courseChapter){
        int rows = courseChapterService.delete(courseChapter.getId());
        if (rows > 0){
            return "SUCCESS";
        }else {
            return "FAIL";
        }
    }
    /**
     * 执行上传课程章节视频
     */
    @ResponseBody
    @RequestMapping("/uploadCourseVideo.action")
    public String uploadMemberPhoto(@RequestParam("file") MultipartFile multipartFile , HttpServletRequest httpServletRequest) throws IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        String id = httpServletRequest.getParameter("id");
        CourseChapter courseChapter = courseChapterService.getById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CourseChapter",JSONObject.toJSON(courseChapter));
        //设置上传视频的保存地址目录
        String dirPath = "C:/tomcat/apache-tomcat-9.0.12/webapps/video/course/";
        File file = new File(dirPath);
        //如果保存文件的地址不存在，就先创建目录
        if (!file.exists()){
            file.mkdirs();
        }
        //重新命名上传文件（课程章节号）
        String newFile = dirPath+id+".mp4";

        try {
            //删除原来的视频
            File file1= new File(newFile);
            if (file1.exists()) {
                file1.delete();
            }
            //使用MultipartFile接口方法完成文件上传到指定位置
            multipartFile.transferTo(file1);
            if (courseChapter.getVideo_path()==null){
                courseChapter.setVideo_path("https://www.iwchuang.cn/video/course/"+id+".mp4");
                courseChapterService.update(courseChapter);
            }
        }catch (Exception e){
            e.printStackTrace();
            return jsonObject.toJSONString();
        }
        jsonObject.put("CourseChapter",JSONObject.toJSON(courseChapter));
        return jsonObject.toJSONString();
    }
}
