package com.ichuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.ichuang.pojo.ProjectApply;
import com.ichuang.service.ProjectApplyService;
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
 * 创业项目申请信息控制器类
 */
@Controller
public class ProjectApplyController {
    //依赖注入
    @Autowired
    private ProjectApplyService projectApplyService;
    /**
     * 通过ID查询创业项目申请信息
     */
    @ResponseBody
    @RequestMapping("/getProjectApply.action")
    public String getProjectApply(Integer id){
        ProjectApply projectApply = projectApplyService.getById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ProjectApply",JSONObject.toJSON(projectApply));
        return jsonObject.toJSONString();
    }
    /**
     * 查询创业项目申请信息列表
     */
    @ResponseBody
    @RequestMapping("/listProjectApply.action")
    public String listProjectApply(@RequestParam(defaultValue="1", required=false)Integer page,
                                    @RequestParam(defaultValue="10",required=false)Integer rows,
                                   Integer id, String project_name, Integer project_id, String apply_name,
                                   String apply_id, Integer status){
        Page<ProjectApply> projectApplyPage= projectApplyService.listAll(page,rows,id,project_name,project_id,apply_name,apply_id,status);
        return JSONObject.toJSON(projectApplyPage).toString();
    }
    /**
     * 修改创业项目申请信息
     */
    @ResponseBody
    @RequestMapping("/updateProjectApply.action")
    public String updateProjectApply(@RequestBody ProjectApply projectApply){
        int rows = projectApplyService.update(projectApply);
        ProjectApply projectApply1 = projectApplyService.getById(projectApply.getId());
        if (rows >0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ProjectApply",JSONObject.toJSON(projectApply1));
            return jsonObject.toJSONString();
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 添加创业项目申请信息
     */
    @ResponseBody
    @RequestMapping("/addProjectApply.action")
    public String addProjectApply(@RequestBody ProjectApply projectApply){
        if (projectApplyService.getById(projectApply.getId())!=null){
            return "该创业项目申请已存在！";
        }
        //受影响的行数
        int rows = projectApplyService.add(projectApply);
        if (rows > 0){
            return "SUCCESS";
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 删除创业项目申请信息
     */
    @ResponseBody
    @RequestMapping("/deleteProjectApply.action")
    public String deleteProjectApply(Integer id){
        int rows = projectApplyService.delete(id);
        if (rows > 0){
            return "SUCCESS";
        }else {
            return "FAIL";
        }
    }
    /**
     * 执行上传创业项目申请表文件
     */
    @ResponseBody
    @RequestMapping("/uploadProjectApplyFile.action")
    public String uploadProjectApplyFile(@RequestParam("file") MultipartFile multipartFile , HttpServletRequest httpServletRequest) throws IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        String id = httpServletRequest.getParameter("id");
        ProjectApply projectApply = projectApplyService.getById(Integer.valueOf(id));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ProjectApply",JSONObject.toJSON(projectApply));
        //设置上传文件的保存地址目录
        String dirPath = "C:/tomcat/apache-tomcat-9.0.12/webapps/files/projectApply/";
        File file = new File(dirPath);
        //如果保存文件的地址不存在，就先创建目录
        if (!file.exists()){
            file.mkdirs();
        }
        //获取上传文件的原始名称
        String originalFilename = multipartFile.getOriginalFilename();
        //获取上传文件的后缀
        String prefix = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        //重新命名上传文件（活动申请号）
        String newFile = dirPath+id+"."+prefix;

        try {
            //删除原来的文件
            File file1= new File(newFile);
            if (file1.exists()) {
                file1.delete();
            }
            //使用MultipartFile接口方法完成文件上传到指定位置
            multipartFile.transferTo(file1);
            if (projectApply.getApply_file()==null){
                projectApply.setApply_file("https://www.iwchuang.cn/files/projectApply/"+id+"_apply_file."+prefix);
                projectApplyService.update(projectApply);
            }
        }catch (Exception e){
            e.printStackTrace();
            return jsonObject.toJSONString();
        }
        jsonObject.put("ProjectApply",JSONObject.toJSON(projectApply));
        return jsonObject.toJSONString();
    }
}
