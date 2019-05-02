package com.ichuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.ichuang.pojo.CourseExerciseResult;
import com.ichuang.service.CourseExerciseResultService;
import com.ichuang.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 课程练习结果信息控制器类
 */
@Controller
public class CourseExerciseResultController {
    //依赖注入
    @Autowired
    private CourseExerciseResultService courseExerciseResultService;

    /**
     * 通过ID查询课程练习结果信息
     */
    @ResponseBody
    @RequestMapping("/getCourseExerciseResult.action")
    public String CourseExerciseResult(Integer id){
        CourseExerciseResult courseExerciseResult = courseExerciseResultService.getById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CourseExerciseResult",JSONObject.toJSON(courseExerciseResult));
        return jsonObject.toJSONString();
    }
    /**
     * 查询课程练习结果信息列表
     */
    @ResponseBody
    @RequestMapping("/listCourseExerciseResult.action")
    public String listCourseExerciseResult(@RequestParam(defaultValue="1", required=false)Integer page,
                                     @RequestParam(defaultValue="10",required=false)Integer rows,
                                           Integer id, String exercise_name, Integer exercise_id,
                                           String member_name, String member_id, Integer score, Integer point){
        Page<CourseExerciseResult> courseExerciseResultPage = courseExerciseResultService.listAll(page,rows,id,exercise_name,exercise_id,
                                                        member_name,member_id,score,point);
        return JSONObject.toJSON(courseExerciseResultPage).toString();
    }
    /**
     * 修改课程练习结果信息
     */
    @ResponseBody
    @RequestMapping("/updateCourseExerciseResult.action")
    public String updateCourseExerciseResult(@RequestBody CourseExerciseResult courseExerciseResult){
        int rows = courseExerciseResultService.update(courseExerciseResult);
        CourseExerciseResult courseExerciseResult1 = courseExerciseResultService.getById(courseExerciseResult.getId());
        if (rows >0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("CourseExerciseResult",JSONObject.toJSON(courseExerciseResult1));
            return jsonObject.toJSONString();
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 添加课程练习结果信息
     */
    @ResponseBody
    @RequestMapping("/addCourseExerciseResult.action")
    public String addCourseExerciseResult(@RequestBody CourseExerciseResult courseExerciseResult){
        if (courseExerciseResultService.getById(courseExerciseResult.getId())!=null){
            return "该课程练习结果已存在！";
        }
        //受影响的行数
        int rows = courseExerciseResultService.add(courseExerciseResult);
        if (rows > 0){
            return "SUCCESS";
        }
        else {
            return "FAIL";
        }
    }
    /**
     * 删除课程练习结果信息
     */
    @ResponseBody
    @RequestMapping("/deleteCourseExerciseResult.action")
    public String deleteCourseExerciseResult(Integer id){
        int rows = courseExerciseResultService.delete(id);
        if (rows > 0){
            return "SUCCESS";
        }else {
            return "FAIL";
        }
    }
}
