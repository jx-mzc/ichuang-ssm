package com.ichuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.ichuang.pojo.Account;
import com.ichuang.pojo.Admin;
import com.ichuang.pojo.Member;
import com.ichuang.service.AccountService;
import com.ichuang.service.AdminService;
import com.ichuang.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 账户控制器类
 */
@Controller
public class AccountController {
    //注入
    @Autowired
    private AccountService accountService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private AdminService adminService;
    /**
     * 向用户登录界面跳转
     */
    @RequestMapping(value = "/login.action",method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }
    /**
     * 用户登录
     */
    @ResponseBody
    @RequestMapping(value = "/login.action",method = RequestMethod.POST)
    public String login(@RequestBody Account account,HttpSession httpSession){
        // 通过账号和密码查询用户
        Account account1 = accountService.findAccount(account.getAccount(),account.getPassword());
        if (account1!=null){
            // 将用户对象添加到Session
            switch (account1.getTypes()){
                case 1:
                    Member member = memberService.getById(account1.getAccount());
                    httpSession.setAttribute("USER_SESSION",member);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    Admin admin = adminService.getById(account1.getAccount());
                    httpSession.setAttribute("USER_SESSION",admin);
                    break;
                case 5:
                    break;
            }

//            //跳转到主页面
//             return "redirect:main";
       }
        //model.addAttribute("msg","账户或密码错误，请重新输入！");
        //返回到登录页面
//        return "login";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Account",JSONObject.toJSON(account1));
        return jsonObject.toJSONString();
    }
    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout.action")
    public String logout(HttpSession session) {
        // 清除Session
        session.invalidate();
        // 重定向到登录页面的跳转方法
        return "redirect:login.action";
    }
    /**
     * 向主页面跳转
     */
    @RequestMapping("/main.action")
    public String toMain(){
        return "main";
    }
    /**
     * 修改账户信息
     */
    @ResponseBody
    @RequestMapping("/updateAccount.action")
    public String updateAccount(@RequestBody Account account){
        int rows = accountService.updateAccount(account);
        if (rows > 0){
            return "SUCCESS";
        }else {
            return "FAIL";
        }
    }
    /**
     * 查询账户信息
     */
    @ResponseBody
    @RequestMapping("/getAccount.action")
    public String getAccount(String id){
        Account account = accountService.getAccountById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Account",JSONObject.toJSON(account));
        return jsonObject.toJSONString();
    }
    /**
     * 添加账户信息
     */
    @ResponseBody
    @RequestMapping("/addAccount.action")
    public String addAccount(@RequestBody Account account){
        if (accountService.getAccountById(account.getAccount())!=null){
            return "该账号已存在！";
        }
        //受影响的行数
        int rows = accountService.addAccount(account);
        if (rows > 0){
            return "SUCCESS";
        }
        else {
            return "FAIL";
        }
    }
}
