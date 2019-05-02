package com.ichuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.ichuang.pojo.CourseExercise;
import com.ichuang.service.CourseExerciseService;
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
public class CourseExerciseController {
    //依赖注入
    @Autowired
    private CourseExerciseService courseExerciseService;
    /**
     * 通过ID查询课程练习信息
     */
    @ResponseBody
    @RequestMapping("/getCourseExercise.action")
    public String getCourseExercise(Integer id){
        CourseExercise courseExercise = courseExerciseService.getById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CourseExercise",JSONObject.toJSON(courseExercise));
        return jsonObject.toJSONString();
    }
    /**
     * 查询课程练习信息列表
     */
    @ResponseBody
    @RequestMapping("/listCourseExercise.action")
    public String listCourseExercise(@RequestParam(defaultValue="1", required=false)Integer page,
                             @RequestParam(defaultValue="10",required=false)Integer rows,
                                     Integer id, String name, String course_name, Integer course_id, String teacher_id){
        Page<CourseExercise> courseExercisePage = courseExerciseService.listAll(page,rows,id,name,course_name,course_id,teacher_id);
        return JSONObject.toJSON(courseExercisePage).toString();
    }
    /**
     * 修改课程练习信息
     */
    @ResponseBody
    @RequestMapping("/updateCourseExercise.action")
    public String updateCourseExercise(@RequestBody CourseExercise courseExercise){
        int rows = courseExerciseService.update(courseExercise);
        CourseExercise courseExercise1 = courseExerciseService.getById(courseExercise.getId());
        if (rows >0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("CourseExercise",JSONObject.toJSON(courseExercise1));
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
    @RequestMapping("/addCourseExercise.action")
    public String addCourseExercise(@RequestBody CourseExercise courseExercise){
        if (courseExerciseService.getById(courseExercise.getId())!=null){
            return "该课程练习已存在！";
        }
        //受影响的行数
        int rows = courseExerciseService.add(courseExercise);
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
    @RequestMapping("/deleteCourseExercise.action")
    public String deleteCourseExercise(Integer id){
        int rows = courseExerciseService.delete(id);
        if (rows > 0){
            return "SUCCESS";
        }else {
            return "FAIL";
        }
    }
}
