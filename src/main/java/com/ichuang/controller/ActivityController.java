package com.ichuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.ichuang.pojo.Activity;
import com.ichuang.service.ActivityService;
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
     * 执行上传创业活动文件
     */
    @ResponseBody
    @RequestMapping("/uploadActivityFile.action")
    public String uploadActivityFile(@RequestParam("file") MultipartFile multipartFile , HttpServletRequest httpServletRequest) throws IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        String id = httpServletRequest.getParameter("id");
        Activity activity = activityService.getById(Integer.valueOf(id));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Activity",JSONObject.toJSON(activity));
        //设置上传文件的保存地址目录
        String dirPath = "C:/tomcat/apache-tomcat-9.0.12/webapps/files/activity/";
        File file = new File(dirPath);
        //如果保存文件的地址不存在，就先创建目录
        if (!file.exists()){
            file.mkdirs();
        }
        //获取上传文件的原始名称
        String originalFilename = multipartFile.getOriginalFilename();
        //获取上传文件的后缀
        String prefix = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        //重新命名上传文件（活动号）
        String newFile = dirPath+id+"."+prefix;

        try {
            //删除原来的文件
            File file1= new File(newFile);
            if (file1.exists()) {
                file1.delete();
            }
            //使用MultipartFile接口方法完成文件上传到指定位置
            multipartFile.transferTo(file1);
            if (activity.getNotification_file()==null){
                activity.setNotification_file("https://www.iwchuang.cn/files/activity/"+id+"_notification_file."+prefix);
                activityService.update(activity);
            }
            if (activity.getApply_file()==null){
                activity.setApply_file("https://www.iwchuang.cn/files/activity/"+id+"_apply_file."+prefix);
                activityService.update(activity);
            }
        }catch (Exception e){
            e.printStackTrace();
            return jsonObject.toJSONString();
        }
        jsonObject.put("Activity",JSONObject.toJSON(activity));
        return jsonObject.toJSONString();
    }
}
