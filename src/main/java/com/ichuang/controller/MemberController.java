package com.ichuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.ichuang.pojo.Member;
import com.ichuang.service.AccountService;
import com.ichuang.service.MemberService;
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
 * 社员控制器类
 */
@Controller
public class MemberController {
    //依赖注入
    @Autowired
    private MemberService memberService;
    @Autowired
    private AccountService accountService;
    /**
     * 通过ID查询社员信息
     */
    @RequestMapping("/getMember.action")
    @ResponseBody
    public String getMemberById(String id){
        Member member = memberService.getById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Member",JSONObject.toJSON(member));
//        String m = JSON.toJSONString(member);
//        jsonObject = JSONObject.parseObject(m);
        return jsonObject.toJSONString();
    }
    /**
     * 修改社员信息
     */
    @ResponseBody
    @RequestMapping("/updateMember.action")
    public String updateMember(@RequestBody Member member){
        int rows = memberService.update(member);
        Member member1 = memberService.getById(member.getId());
        if (rows >0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Member",JSONObject.toJSON(member1));
            return jsonObject.toJSONString();
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 执行上传头像
     */
    @ResponseBody
    @RequestMapping("/uploadMemberPhoto.action")
    public String uploadMemberPhoto(@RequestParam("file") MultipartFile multipartFile , HttpServletRequest httpServletRequest) throws IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        String id = httpServletRequest.getParameter("id");
        Member member = memberService.getById(id);
        JSONObject jsonObject = new JSONObject();
        String path = member.getPhoto();
        String dirPath = "C:/tomcat/apache-tomcat-9.0.12/webapps/images/member/";

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
            member.setPhoto("https://www.iwchuang.cn/images/member/"+newName);
            Member member1 = new Member();
            member1.setId(member.getId());
            member1.setPhoto("https://www.iwchuang.cn/images/member/"+newName);
            memberService.update(member1);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("Member",JSONObject.toJSON(member));
            return jsonObject.toJSONString();
        }
        jsonObject.put("Member",JSONObject.toJSON(member));
        return jsonObject.toJSONString();
    }
    /**
     * 查询社员信息列表
     */
    @ResponseBody
    @RequestMapping("/listMember.action")
    public String listMember(@RequestParam(defaultValue="1", required=false)Integer page,
                             @RequestParam(defaultValue="10",required=false)Integer rows,
                             String id,String name,String school_name,String club_name){
        Page<Member> memberPage = memberService.listAll(page,rows,id,name,school_name,club_name);
        return JSONObject.toJSON(memberPage).toString();
    }
    /**
     * 添加社员信息
     */
    @ResponseBody
    @RequestMapping("/addMember.action")
    public String addMember(@RequestBody Member member){
        if (memberService.getById(member.getId())!=null){
            return "该社员已存在！";
        }
        //受影响的行数
        int rows = memberService.add(member);
        if (rows > 0){
            return "SUCCESS";
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 删除社员信息
     */
    @ResponseBody
    @RequestMapping("/deleteMember.action")
    public String deleteMember(String id){
        int rows = memberService.delete(id);
        accountService.deleteAccount(id);
        if (rows > 0){
            return "SUCCESS";
        }else {
            return "FAIL";
        }
    }
}
