package com.ichuang.pojo;

import java.io.Serializable;

/**
 * 课程练习问题持久化类
 */
public class CourseExerciseQuestion implements Serializable {
    private String id;
    private int number;     //题号
    private String question;
    private int score;      //分值
    private String choice_A;
    private String choice_B;
    private String choice_C;
    private String choice_D;
    private String answer;  //答案
    private String exercise_id;
    private Integer start;            // 起始行
    private Integer rows;             // 所取行数

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getChoice_A() {
        return choice_A;
    }

    public void setChoice_A(String choice_A) {
        this.choice_A = choice_A;
    }

    public String getChoice_B() {
        return choice_B;
    }

    public void setChoice_B(String choice_B) {
        this.choice_B = choice_B;
    }

    public String getChoice_C() {
        return choice_C;
    }

    public void setChoice_C(String choice_C) {
        this.choice_C = choice_C;
    }

    public String getChoice_D() {
        return choice_D;
    }

    public void setChoice_D(String choice_D) {
        this.choice_D = choice_D;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(String exercise_id) {
        this.exercise_id = exercise_id;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
