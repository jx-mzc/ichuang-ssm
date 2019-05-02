package com.ichuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.ichuang.pojo.CourseExerciseQuestion;
import com.ichuang.service.CourseExerciseQuestionService;
import com.ichuang.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 课程练习题目信息控制器类
 */
@Controller
public class CourseExerciseQuestionController {
    //依赖注入
    @Autowired
    private CourseExerciseQuestionService courseExerciseQuestionService;

    /**
     * 通过ID查询课程练习题目信息
     */
    @ResponseBody
    @RequestMapping("/getCourseExerciseQuestion.action")
    public String getCourseExerciseQuestion(Integer id){
        CourseExerciseQuestion courseExerciseQuestion = courseExerciseQuestionService.getById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CourseExerciseQuestion",JSONObject.toJSON(courseExerciseQuestion));
        return jsonObject.toJSONString();
    }
    /**
     * 查询课程练习题目信息列表
     */
    @ResponseBody
    @RequestMapping("/listCourseExerciseQuestion.action")
    public String listCourseExerciseQuestion(@RequestParam(defaultValue="1", required=false)Integer page,
                                     @RequestParam(defaultValue="10",required=false)Integer rows,
                                             Integer id, Integer number, Integer score, String answer, Integer exercise_id){
        Page<CourseExerciseQuestion> courseExerciseQuestionPage = courseExerciseQuestionService.listAll(page,rows,id,number,score,answer,exercise_id);
        return JSONObject.toJSON(courseExerciseQuestionPage).toString();
    }
    /**
     * 修改课程练习题目信息
     */
    @ResponseBody
    @RequestMapping("/updateCourseExerciseQuestion.action")
    public String updateCourseExerciseQuestion(@RequestBody CourseExerciseQuestion courseExerciseQuestion){
        int rows = courseExerciseQuestionService.update(courseExerciseQuestion);
        CourseExerciseQuestion courseExerciseQuestion1 = courseExerciseQuestionService.getById(courseExerciseQuestion.getId());
        if (rows >0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("CourseExerciseQuestion",JSONObject.toJSON(courseExerciseQuestion1));
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
    @RequestMapping("/addCourseExerciseQuestion.action")
    public String addCourseExerciseQuestion(@RequestBody CourseExerciseQuestion courseExerciseQuestion){
        if (courseExerciseQuestionService.getById(courseExerciseQuestion.getId())!=null){
            return "该课程练习题目已存在！";
        }
        //受影响的行数
        int rows = courseExerciseQuestionService.add(courseExerciseQuestion);
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
    @RequestMapping("/deleteCourseExerciseQuestion.action")
    public String deleteCourseExerciseQuestion(Integer id){
        int rows = courseExerciseQuestionService.delete(id);
        if (rows > 0){
            return "SUCCESS";
        }else {
            return "FAIL";
        }
    }
}
