package com.ichuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.ichuang.pojo.Activity;
import com.ichuang.service.ActivityService;
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
 * 创业活动信息控制器类
 */
@Controller
public class ActivityController {
    //依赖注入
    @Autowired
    private ActivityService activityService;
    /**
     * 通过ID查询创业活动信息
     */
    @ResponseBody
    @RequestMapping("/getActivity.action")
    public String getActivity(Integer id){
        Activity activity = activityService.getById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Activity",JSONObject.toJSON(activity));
        return jsonObject.toJSONString();
    }
    /**
     * 查询创业活动信息列表
     */
    @ResponseBody
    @RequestMapping("/listActivity.action")
    public String listActivity(@RequestParam(defaultValue="1", required=false)Integer page,
                             @RequestParam(defaultValue="10",required=false)Integer rows,
                               Integer id, String name, String start_time, String end_time, String post, String hold){
        Page<Activity> activityPage= activityService.listAll(page,rows,id,name,start_time,end_time,post,hold);
        return JSONObject.toJSON(activityPage).toString();
    }
    /**
     * 修改创业活动信息
     */
    @ResponseBody
    @RequestMapping("/updateActivity.action")
    public String updateActivity(@RequestBody Activity activity){
        int rows = activityService.update(activity);
        Activity activity1 = activityService.getById(activity.getId());
        if (rows >0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Activity",JSONObject.toJSON(activity1));
            return jsonObject.toJSONString();
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 添加创业活动信息
     */
    @ResponseBody
    @RequestMapping("/addActivity.action")
    public String addActivity(@RequestBody Activity activity){
        if (activityService.getById(activity.getId())!=null){
            return "该创业活动已存在！";
        }
        //受影响的行数
        int rows = activityService.add(activity);
        if (rows > 0){
            return "SUCCESS";
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 删除创业活动信息
     */
    @ResponseBody
    @RequestMapping("/deleteActivity.action")
    public String deleteActivity(Integer id){
        int rows = activityService.delete(id);
        if (rows > 0){
            return "SUCCESS";
        }else {
            return "FAIL";
        }
    }
    /**
     * 执行上传创业活动通知文件
     */
    @ResponseBody
    @RequestMapping("/uploadActivityNotificationFile.action")
    public String uploadActivityNotificationFile(@RequestParam("file") MultipartFile multipartFile , HttpServletRequest httpServletRequest) throws IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        String id = httpServletRequest.getParameter("id");
        Activity activity = activityService.getById(Integer.valueOf(id));
        JSONObject jsonObject = new JSONObject();

        String path = activity.getNotification_file();

        //设置上传文件的保存地址目录
        String dirPath = "C:/tomcat/apache-tomcat-9.0.12/webapps/files/activity/";

        //获取上传文件的原始名称
        String originalFilename = multipartFile.getOriginalFilename();
        //获取上传文件的后缀
        String prefix = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        //重新命名上传文件（活动号）
        String newName =  UUID.randomUUID()+"_notification_file"+"."+prefix;

        try {
            File file;
            if (StringUtils.isNoneBlank(path)){
                String filename = path.substring(path.lastIndexOf("/")+1);
                file = new File(dirPath+filename);
                file.delete();
                file = new File(dirPath+newName);
            } else {
                file = new File(dirPath+newName);
                //如果保存文件的地址不存在，就先创建目录
                file.mkdirs();
            }
            //使用MultipartFile接口方法完成文件上传到指定位置
            multipartFile.transferTo(file);
            activity.setNotification_file("https://www.iwchuang.cn/files/activity/"+newName);
            Activity activity1 = new Activity();
            activity1.setId(activity.getId());
            activity1.setNotification_file("https://www.iwchuang.cn/files/activity/"+newName);
            activityService.update(activity1);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("Activity",JSONObject.toJSON(activity));
            return jsonObject.toJSONString();
        }
        jsonObject.put("Activity",JSONObject.toJSON(activity));
        return jsonObject.toJSONString();
    }
    /**
     * 执行上传创业活动报名文件
     */
    @ResponseBody
    @RequestMapping("/uploadActivityApplyFile.action")
    public String uploadActivityApplyFile(@RequestParam("file") MultipartFile multipartFile , HttpServletRequest httpServletRequest) throws IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        String id = httpServletRequest.getParameter("id");
        Activity activity = activityService.getById(Integer.valueOf(id));
        JSONObject jsonObject = new JSONObject();
        String path = activity.getApply_file();

        //设置上传文件的保存地址目录
        String dirPath = "C:/tomcat/apache-tomcat-9.0.12/webapps/files/activity/";

        //获取上传文件的原始名称
        String originalFilename = multipartFile.getOriginalFilename();
        //获取上传文件的后缀
        String prefix = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        //重新命名上传文件（活动号）
        String newName =  UUID.randomUUID()+"_apply_file"+"."+prefix;

        try {
            File file;
            if (StringUtils.isNoneBlank(path)){
                String filename = path.substring(path.lastIndexOf("/")+1);
                file = new File(dirPath+filename);
                file.delete();
                file = new File(dirPath+newName);
            }else {
                file = new File(dirPath+newName);
                //如果保存文件的地址不存在，就先创建目录
                file.mkdirs();
            }
            //使用MultipartFile接口方法完成文件上传到指定位置
            multipartFile.transferTo(file);
            activity.setApply_file("https://www.iwchuang.cn/files/activity/"+newName);
            Activity activity1 = new Activity();
            activity1.setId(activity.getId());
            activity1.setApply_file("https://www.iwchuang.cn/files/activity/"+newName);
            activityService.update(activity1);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("Activity",JSONObject.toJSON(activity));
            return jsonObject.toJSONString();
        }
        jsonObject.put("Activity",JSONObject.toJSON(activity));
        return jsonObject.toJSONString();
    }
}
