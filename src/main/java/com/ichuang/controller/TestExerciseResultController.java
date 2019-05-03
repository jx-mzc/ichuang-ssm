package com.ichuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.ichuang.pojo.TestExerciseResult;
import com.ichuang.service.TestExerciseResultService;
import com.ichuang.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 考核结果信息控制器类
 */
@Controller
public class TestExerciseResultController {
    //依赖注入
    @Autowired
    private TestExerciseResultService testExerciseResultService;

    /**
     * 通过ID查询课程练习结果信息
     */
    @ResponseBody
    @RequestMapping("/getTestExerciseResult.action")
    public String TestExerciseResult(Integer id){
        TestExerciseResult testExerciseResult = testExerciseResultService.getById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("TestExerciseResult",JSONObject.toJSON(testExerciseResult));
        return jsonObject.toJSONString();
    }
    /**
     * 查询考核结果信息列表
     */
    @ResponseBody
    @RequestMapping("/listTestExerciseResult.action")
    public String listTestExerciseResult(@RequestParam(defaultValue="1", required=false)Integer page,
                                           @RequestParam(defaultValue="10",required=false)Integer rows,
                                           Integer id, String assessment_name, Integer assessment_id,
                                           String member_name, String member_id, Integer score){
        Page<TestExerciseResult> testExerciseResultPage = testExerciseResultService.listAll(page,rows,id,assessment_name,assessment_id,
                member_name,member_id,score);
        return JSONObject.toJSON(testExerciseResultPage).toString();
    }
    /**
     * 修改考核结果信息
     */
    @ResponseBody
    @RequestMapping("/updateTestExerciseResult.action")
    public String updateTestExerciseResult(@RequestBody TestExerciseResult testExerciseResult){
        int rows = testExerciseResultService.update(testExerciseResult);
        TestExerciseResult testExerciseResult1 = testExerciseResultService.getById(testExerciseResult.getId());
        if (rows >0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("TestExerciseResult",JSONObject.toJSON(testExerciseResult1));
            return jsonObject.toJSONString();
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 添加考核结果信息
     */
    @ResponseBody
    @RequestMapping("/addTestExerciseResult.action")
    public String addCourseExerciseResult(@RequestBody TestExerciseResult testExerciseResult){
        if (testExerciseResultService.getById(testExerciseResult.getId())!=null){
            return "该课程练习结果已存在！";
        }
        //受影响的行数
        int rows = testExerciseResultService.add(testExerciseResult);
        if (rows > 0){
            return "SUCCESS";
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 删除考核结果信息
     */
    @ResponseBody
    @RequestMapping("/deleteTestExerciseResult.action")
    public String deleteTestExerciseResult(Integer id){
        int rows = testExerciseResultService.delete(id);
        if (rows > 0){
            return "SUCCESS";
        }else {
            return "FAIL";
        }
    }
}

