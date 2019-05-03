package com.ichuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.ichuang.pojo.CompetitionApply;
import com.ichuang.service.CompetitionApplyService;
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
 * 创业大赛报名信息控制器类
 */
@Controller
public class CompetitionApplyController {
    //依赖注入
    @Autowired
    private CompetitionApplyService competitionApplyService;
    /**
     * 通过ID查询创业大赛报名信息
     */
    @ResponseBody
    @RequestMapping("/getCompetitionApply.action")
    public String getCompetitionApply(Integer id){
        CompetitionApply competitionApply = competitionApplyService.getById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CompetitionApply",JSONObject.toJSON(competitionApply));
        return jsonObject.toJSONString();
    }
    /**
     * 查询创业大赛报名信息列表
     */
    @ResponseBody
    @RequestMapping("/listCompetitionApply.action")
    public String listCompetitionApply(@RequestParam(defaultValue="1", required=false)Integer page,
                                    @RequestParam(defaultValue="10",required=false)Integer rows,
                                       Integer id, String competition_name, Integer competition_id, String apply_name,
                                       String apply_id,String name,String member_phone,String type,String progress){
        Page<CompetitionApply> competitionApplyPage= competitionApplyService.listAll(page,rows,id,competition_name,competition_id,
                apply_name,apply_id,name,member_phone,type,progress);
        return JSONObject.toJSON(competitionApplyPage).toString();
    }
    /**
     * 修改创业大赛报名信息
     */
    @ResponseBody
    @RequestMapping("/updateCompetitionApply.action")
    public String updateCompetitionApply(@RequestBody CompetitionApply competitionApply){
        int rows = competitionApplyService.update(competitionApply);
        CompetitionApply competitionApply1 = competitionApplyService.getById(competitionApply.getId());
        if (rows >0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("CompetitionApply",JSONObject.toJSON(competitionApply1));
            return jsonObject.toJSONString();
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 添加创业大赛报名信息
     */
    @ResponseBody
    @RequestMapping("/addCompetitionApply.action")
    public String addCompetitionApply(@RequestBody CompetitionApply competitionApply){
        if (competitionApplyService.getById(competitionApply.getId())!=null){
            return "该创业大赛报名已存在！";
        }
        //受影响的行数
        int rows = competitionApplyService.add(competitionApply);
        if (rows > 0){
            return "SUCCESS";
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 删除创业大赛报名信息
     */
    @ResponseBody
    @RequestMapping("/deleteCompetitionApply.action")
    public String deleteCompetitionApply(Integer id){
        int rows = competitionApplyService.delete(id);
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
    @RequestMapping("/uploadCompetitionApplyFile.action")
    public String uploadCompetitionApplyFile(@RequestParam("file") MultipartFile multipartFile , HttpServletRequest httpServletRequest) throws IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        String id = httpServletRequest.getParameter("id");
        CompetitionApply competitionApply = competitionApplyService.getById(Integer.valueOf(id));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CompetitionApply",JSONObject.toJSON(competitionApply));
        //设置上传文件的保存地址目录
        String dirPath = "C:/tomcat/apache-tomcat-9.0.12/webapps/files/competitionApply/";
        File file = new File(dirPath);
        //如果保存文件的地址不存在，就先创建目录
        if (!file.exists()){
            file.mkdirs();
        }
        //获取上传文件的原始名称
        String originalFilename = multipartFile.getOriginalFilename();
        //获取上传文件的后缀
        String prefix = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        //重新命名上传文件（大赛报名号）
        String newFile = dirPath+id+"."+prefix;

        try {
            //删除原来的文件
            File file1= new File(newFile);
            if (file1.exists()) {
                file1.delete();
            }
            //使用MultipartFile接口方法完成文件上传到指定位置
            multipartFile.transferTo(file1);
            if (competitionApply.getPlan_file()==null){
                competitionApply.setPlan_file("https://www.iwchuang.cn/files/competitionApply/"+id+"_apply_file."+prefix);
                competitionApplyService.update(competitionApply);
            }
        }catch (Exception e){
            e.printStackTrace();
            return jsonObject.toJSONString();
        }
        jsonObject.put("CompetitionApply",JSONObject.toJSON(competitionApply));
        return jsonObject.toJSONString();
    }
}
