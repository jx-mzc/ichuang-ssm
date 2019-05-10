package com.ichuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.ichuang.pojo.ActivityApply;
import com.ichuang.service.ActivityApplyService;
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
 * 创业活动申请信息控制器类
 */
@Controller
public class ActivityApplyController {
    //依赖注入
    @Autowired
    private ActivityApplyService activityApplyService;
    /**
     * 通过ID查询创业活动申请信息
     */
    @ResponseBody
    @RequestMapping("/getActivityApply.action")
    public String getActivityApply(Integer id){
        ActivityApply activityApply = activityApplyService.getById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ActivityApply",JSONObject.toJSON(activityApply));
        return jsonObject.toJSONString();
    }
    /**
     * 查询创业活动申请信息列表
     */
    @ResponseBody
    @RequestMapping("/listActivityApply.action")
    public String listActivityApply(@RequestParam(defaultValue="1", required=false)Integer page,
                               @RequestParam(defaultValue="10",required=false)Integer rows,
                                    Integer id, String activity_name, Integer activity_id, String apply_name, String apply_id){
        Page<ActivityApply> activityApplyPage= activityApplyService.listAll(page,rows,id,activity_name,activity_id,apply_name,apply_id);
        return JSONObject.toJSON(activityApplyPage).toString();
    }
    /**
     * 修改创业活动申请信息
     */
    @ResponseBody
    @RequestMapping("/updateActivityApply.action")
    public String updateActivityApply(@RequestBody ActivityApply activityApply){
        int rows = activityApplyService.update(activityApply);
        ActivityApply activityApply1 = activityApplyService.getById(activityApply.getId());
        if (rows >0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ActivityApply",JSONObject.toJSON(activityApply1));
            return jsonObject.toJSONString();
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 添加创业活动申请信息
     */
    @ResponseBody
    @RequestMapping("/addActivityApply.action")
    public String addActivityApply(@RequestBody ActivityApply activityApply){
        if (activityApplyService.getById(activityApply.getId())!=null){
            return "该创业活动申请已存在！";
        }
        //受影响的行数
        int rows = activityApplyService.add(activityApply);
        if (rows > 0){
            return "SUCCESS";
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 删除创业活动申请信息
     */
    @ResponseBody
    @RequestMapping("/deleteActivityApply.action")
    public String deleteActivityApply(Integer id){
        int rows = activityApplyService.delete(id);
        if (rows > 0){
            return "SUCCESS";
        }else {
            return "FAIL";
        }
    }
    /**
     * 执行上传创业活动申请表文件
     */
    @ResponseBody
    @RequestMapping("/uploadActivityApply.action")
    public String uploadActivityApply(@RequestParam("file") MultipartFile multipartFile , HttpServletRequest httpServletRequest) throws IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        String id = httpServletRequest.getParameter("id");
        ActivityApply activityApply = activityApplyService.getById(Integer.valueOf(id));
        JSONObject jsonObject = new JSONObject();
        String path = activityApply.getApply_file();

        //设置上传文件的保存地址目录
        String dirPath = "C:/tomcat/apache-tomcat-9.0.12/webapps/files/activityApply/";

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
            activityApply.setApply_file("https://www.iwchuang.cn/files/activityApply/"+newName);
            ActivityApply activityApply1 = new ActivityApply();
            activityApply1.setId(activityApply.getId());
            activityApply1.setApply_file("https://www.iwchuang.cn/files/activityApply/"+newName);
            activityApplyService.update(activityApply1);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("ActivityApply",JSONObject.toJSON(activityApply));
            return jsonObject.toJSONString();
        }
        jsonObject.put("ActivityApply",JSONObject.toJSON(activityApply));
        return jsonObject.toJSONString();
    }
}
