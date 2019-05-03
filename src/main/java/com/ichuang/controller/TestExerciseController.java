package com.ichuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.ichuang.pojo.TestExercise;
import com.ichuang.service.TestExerciseService;
import com.ichuang.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 课程练习信息控制器类
 */
@Controller
public class TestExerciseController {
    //依赖注入
    @Autowired
    private TestExerciseService testExerciseService;
    /**
     * 通过ID查询课程练习信息
     */
    @ResponseBody
    @RequestMapping("/getTestExercise.action")
    public String getTestExercise(Integer id){
        TestExercise testExercise = testExerciseService.getById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("TestExercise",JSONObject.toJSON(testExercise));
        return jsonObject.toJSONString();
    }
    /**
     * 查询课程练习信息列表
     */
    @ResponseBody
    @RequestMapping("/listTestExercise.action")
    public String listTestExercise(@RequestParam(defaultValue="1", required=false)Integer page,
                                     @RequestParam(defaultValue="10",required=false)Integer rows,
                                     Integer id, String name, String post_name, Integer post_id){
        Page<TestExercise> testExercisePage = testExerciseService.listAll(page,rows,id, name, post_name, post_id);
        return JSONObject.toJSON(testExercisePage).toString();
    }
    /**
     * 修改课程练习信息
     */
    @ResponseBody
    @RequestMapping("/updateTestExercise.action")
    public String updateTestExercise(@RequestBody TestExercise testExercise){
        int rows = testExerciseService.update(testExercise);
        TestExercise courseExercise1 = testExerciseService.getById(testExercise.getId());
        if (rows >0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("TestExercise",JSONObject.toJSON(courseExercise1));
            return jsonObject.toJSONString();
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 添加课程练习信息
     */
    @ResponseBody
    @RequestMapping("/addTestExercise.action")
    public String addTestExercise(@RequestBody TestExercise testExercise){
        if (testExerciseService.getById(testExercise.getId())!=null){
            return "该考核已存在！";
        }
        //受影响的行数
        int rows = testExerciseService.add(testExercise);
        if (rows > 0){
            return "SUCCESS";
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 删除课程练习信息
     */
    @ResponseBody
    @RequestMapping("/deleteTestExercise.action")
    public String deleteTestExercise(Integer id){
        int rows = testExerciseService.delete(id);
        if (rows > 0){
            return "SUCCESS";
        }else {
            return "FAIL";
        }
    }
}
