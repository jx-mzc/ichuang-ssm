package com.ichuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.ichuang.pojo.TestExerciseQuestion;
import com.ichuang.service.TestExerciseQuestionService;
import com.ichuang.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestExerciseQuestionController {
    //依赖注入
    @Autowired
    private TestExerciseQuestionService testExerciseQuestionService;

    /**
     * 通过ID查询课程练习题目信息
     */
    @ResponseBody
    @RequestMapping("/getTestExerciseQuestion.action")
    public String getTestExerciseQuestion(Integer id){
        TestExerciseQuestion courseExerciseQuestion = testExerciseQuestionService.getById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CourseExerciseQuestion",JSONObject.toJSON(courseExerciseQuestion));
        return jsonObject.toJSONString();
    }
    /**
     * 查询课程练习题目信息列表
     */
    @ResponseBody
    @RequestMapping("/listTestExerciseQuestion.action")
    public String listTestExerciseQuestion(@RequestParam(defaultValue="1", required=false)Integer page,
                                             @RequestParam(defaultValue="10",required=false)Integer rows,
                                             Integer id, Integer number, Integer score, String answer, Integer exercise_id){
        Page<TestExerciseQuestion> testExerciseQuestionPage = testExerciseQuestionService.listAll(page,rows,id,number,score,answer,exercise_id);
        return JSONObject.toJSON(testExerciseQuestionPage).toString();
    }
    /**
     * 修改课程练习题目信息
     */
    @ResponseBody
    @RequestMapping("/updateTestExerciseQuestion.action")
    public String updateTestExerciseQuestion(@RequestBody TestExerciseQuestion testExerciseQuestion){
        int rows = testExerciseQuestionService.update(testExerciseQuestion);
        TestExerciseQuestion testExerciseQuestion1 = testExerciseQuestionService.getById(testExerciseQuestion.getId());
        if (rows >0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("TestExerciseQuestion",JSONObject.toJSON(testExerciseQuestion1));
            return jsonObject.toJSONString();
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 添加课程练习题目信息
     */
    @ResponseBody
    @RequestMapping("/addTestExerciseQuestion.action")
    public String addTestExerciseQuestion(@RequestBody TestExerciseQuestion testExerciseQuestion){
        if (testExerciseQuestionService.getById(testExerciseQuestion.getId())!=null){
            return "该课程练习题目已存在！";
        }
        //受影响的行数
        int rows = testExerciseQuestionService.add(testExerciseQuestion);
        if (rows > 0){
            return "SUCCESS";
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 删除课程练习题目信息
     */
    @ResponseBody
    @RequestMapping("/deleteTestExerciseQuestion.action")
    public String deleteTestExerciseQuestion(Integer id){
        int rows = testExerciseQuestionService.delete(id);
        if (rows > 0){
            return "SUCCESS";
        }else {
            return "FAIL";
        }
    }
}
