package com.ichuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.ichuang.pojo.Competition;
import com.ichuang.service.CompetitionService;
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
 * 创业大赛信息控制器类
 */
@Controller
public class CompetitionController {
    //依赖注入
    @Autowired
    private CompetitionService competitionService;

    /**
     * 通过ID查询创业大赛信息
     */
    @ResponseBody
    @RequestMapping("/getCompetition.action")
    public String getCompetition(Integer id){
        Competition competition = competitionService.getById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Competition",JSONObject.toJSON(competition));
        return jsonObject.toJSONString();
    }
    /**
     * 查询创业大赛信息列表
     */
    @ResponseBody
    @RequestMapping("/listCompetition.action")
    public String listCompetition(@RequestParam(defaultValue="1", required=false)Integer page,
                               @RequestParam(defaultValue="10",required=false)Integer rows, Integer id,
                                  String name, String start_time, String end_time, String post, String hold, String rank){
        Page<Competition> competitionPage= competitionService.listAll(page,rows,id,name,start_time,end_time,post,hold,rank);
        return JSONObject.toJSON(competitionPage).toString();
    }
    /**
     * 修改创业大赛信息
     */
    @ResponseBody
    @RequestMapping("/updateCompetition.action")
    public String updateCompetition(@RequestBody Competition competition){
        int rows = competitionService.update(competition);
        Competition competition1 = competitionService.getById(competition.getId());
        if (rows >0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Competition",JSONObject.toJSON(competition1));
            return jsonObject.toJSONString();
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 添加创业大赛信息
     */
    @ResponseBody
    @RequestMapping("/addCompetition.action")
    public String addCompetition(@RequestBody Competition competition){
        if (competitionService.getById(competition.getId())!=null){
            return "该创业大赛已存在！";
        }
        //受影响的行数
        int rows = competitionService.add(competition);
        if (rows > 0){
            return "SUCCESS";
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 删除创业大赛信息
     */
    @ResponseBody
    @RequestMapping("/deleteCompetition.action")
    public String deleteCompetition(Integer id){
        int rows = competitionService.delete(id);
        if (rows > 0){
            return "SUCCESS";
        }else {
            return "FAIL";
        }
    }
    /**
     * 执行上传创业大赛文件
     */
    @ResponseBody
    @RequestMapping("/uploadCompetitionFile.action")
    public String uploadCompetitionFile(@RequestParam("file") MultipartFile multipartFile , HttpServletRequest httpServletRequest) throws IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        String id = httpServletRequest.getParameter("id");
        Competition competition = competitionService.getById(Integer.valueOf(id));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Competition",JSONObject.toJSON(competition));
        //设置上传文件的保存地址目录
        String dirPath = "C:/tomcat/apache-tomcat-9.0.12/webapps/files/competition/";
        File file = new File(dirPath);
        //如果保存文件的地址不存在，就先创建目录
        if (!file.exists()){
            file.mkdirs();
        }
        //获取上传文件的原始名称
        String originalFilename = multipartFile.getOriginalFilename();
        //获取上传文件的后缀
        String prefix = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        //重新命名上传文件（大赛号）
        String newFile = dirPath+id+"."+prefix;

        try {
            //删除原来的文件
            File file1= new File(newFile);
            if (file1.exists()) {
                file1.delete();
            }
            //使用MultipartFile接口方法完成文件上传到指定位置
            multipartFile.transferTo(file1);
            if (competition.getNotification_file()==null){
                competition.setNotification_file("https://www.iwchuang.cn/files/competition/"+id+"_notification_file."+prefix);
                competitionService.update(competition);
            }
        }catch (Exception e){
            e.printStackTrace();
            return jsonObject.toJSONString();
        }
        jsonObject.put("Competition",JSONObject.toJSON(competition));
        return jsonObject.toJSONString();
    }
}
