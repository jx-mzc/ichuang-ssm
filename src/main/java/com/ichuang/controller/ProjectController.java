package com.ichuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.ichuang.pojo.Project;
import com.ichuang.service.ProjectService;
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
 * 创业项目信息控制器类
 */
@Controller
public class ProjectController {
    //依赖注入
    @Autowired
    private ProjectService projectService;
    /**
     * 通过ID查询创业项目信息
     */
    @ResponseBody
    @RequestMapping("/getProject.action")
    public String getProject(Integer id){
        Project project = projectService.getById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Project",JSONObject.toJSON(project));
        return jsonObject.toJSONString();
    }
    /**
     * 查询创业项目信息列表
     */
    @ResponseBody
    @RequestMapping("/listProject.action")
    public String listProject(@RequestParam(defaultValue="1", required=false)Integer page,
                               @RequestParam(defaultValue="10",required=false)Integer rows,
                              Integer id, String name, String start_time,
                              String end_time, String post_name, String post_id, String type, String rank){
        Page<Project> projectPage= projectService.listAll(page,rows,id,name,start_time,end_time,post_name,post_id,type,rank);
        return JSONObject.toJSON(projectPage).toString();
    }
    /**
     * 修改创业项目信息
     */
    @ResponseBody
    @RequestMapping("/updateProject.action")
    public String updateProject(@RequestBody Project project){
        int rows = projectService.update(project);
        Project project1 = projectService.getById(project.getId());
        if (rows >0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Project",JSONObject.toJSON(project1));
            return jsonObject.toJSONString();
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 添加创业项目信息
     */
    @ResponseBody
    @RequestMapping("/addProject.action")
    public String addProject(@RequestBody Project project){
        if (projectService.getById(project.getId())!=null){
            return "该创业项目已存在！";
        }
        //受影响的行数
        int rows = projectService.add(project);
        if (rows > 0){
            return "SUCCESS";
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 删除创业项目信息
     */
    @ResponseBody
    @RequestMapping("/deleteProject.action")
    public String deleteProject(Integer id){
        int rows = projectService.delete(id);
        if (rows > 0){
            return "SUCCESS";
        }else {
            return "FAIL";
        }
    }
    /**
     * 执行上传创业项目文件
     */
    @ResponseBody
    @RequestMapping("/uploadProjectFile.action")
    public String uploadProjectFile(@RequestParam("file") MultipartFile multipartFile , HttpServletRequest httpServletRequest) throws IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        String id = httpServletRequest.getParameter("id");
        Project project = projectService.getById(Integer.valueOf(id));
        JSONObject jsonObject = new JSONObject();
        String path = project.getProject_file();


        //设置上传文件的保存地址目录
        String dirPath = "C:/tomcat/apache-tomcat-9.0.12/webapps/files/project/";

        //获取上传文件的原始名称
        String originalFilename = multipartFile.getOriginalFilename();
        //获取上传文件的后缀
        String prefix = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        //重新命名上传文件（项目号）
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
            project.setProject_file("https://www.iwchuang.cn/files/project/"+newName);
            Project project1 = new Project();
            project1.setId(project.getId());
            project1.setProject_file("https://www.iwchuang.cn/files/project/"+newName);
            projectService.update(project1);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("Project",JSONObject.toJSON(project));
            return jsonObject.toJSONString();
        }
        jsonObject.put("Project",JSONObject.toJSON(project));
        return jsonObject.toJSONString();
    }
    /**
     * 执行上传创业项目照片
     */
    @ResponseBody
    @RequestMapping("/uploadProjectPhoto.action")
    public String uploadProjectPhoto(@RequestParam("file") MultipartFile multipartFile , HttpServletRequest httpServletRequest) throws IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        String id = httpServletRequest.getParameter("id");
        Project project = projectService.getById(Integer.valueOf(id));
        JSONObject jsonObject = new JSONObject();
        String path = project.getPhoto();

        //设置上传文件的保存地址目录
        String dirPath = "C:/tomcat/apache-tomcat-9.0.12/webapps/images/project/";

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
            project.setPhoto("https://www.iwchuang.cn/images/project/"+newName);
            Project project1 = new Project();
            project1.setId(project.getId());
            project1.setPhoto("https://www.iwchuang.cn/files/project/"+newName);
            projectService.update(project1);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("Project",JSONObject.toJSON(project));
            return jsonObject.toJSONString();
        }
        jsonObject.put("Project",JSONObject.toJSON(project));
        return jsonObject.toJSONString();
    }
}
