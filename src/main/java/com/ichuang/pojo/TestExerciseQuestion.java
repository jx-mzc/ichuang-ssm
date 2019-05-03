package com.ichuang.pojo;

import java.io.Serializable;

public class TestExerciseQuestion implements Serializable {
    private Integer id;
    private Integer number;     //题号
    private String question;
    private Integer score;      //分值
    private String choice_A;
    private String choice_B;
    private String choice_C;
    private String choice_D;
    private String answer;  //答案
    private Integer assessment_id;
    private Integer start;            // 起始行
    private Integer rows;             // 所取行数

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
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

    public Integer getAssessment_id() {
        return assessment_id;
    }

    public void setAssessment_id(Integer assessment_id) {
        this.assessment_id = assessment_id;
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
