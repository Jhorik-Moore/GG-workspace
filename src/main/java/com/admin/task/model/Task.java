package com.admin.task.model;

import javax.persistence.*;


@Entity
@Table(name = "task")
public class Task {
       @Id
       @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Long id;
        private String task_number;
        private String product;
        private String name;
        private String surname;
        private String father_name;
        private String mob;
        private String email;
        @Column(name="_check")
        private String check;
        @Column( length = 30000 )
        private String detail_problem;
        private String date_time;
        private String business;
        private String category;
        private String code_close;
        private String status;
        private String hard;
        @Column( length = 3000 )
        private String solution;
        @Column( length = 1000 )
        private String remark;


    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getDetail_problem() {
        return detail_problem;
    }

    public void setDetail_problem(String detail_problem) {
        this.detail_problem = detail_problem;
    }

    public String getTask_number() {
        return task_number;
    }

    public void setTask_number(String task_number) {
        this.task_number = task_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getBusiness() {
        return business;
    }

    public String getCategory() {
        return category;
    }

    public String getCode_close() {
        return code_close;
    }

    public String getStatus() {
        return status;
    }

    public String getHard() {
        return hard;
    }

    public String getSolution() {
        return solution;
    }

    public String getRemark() {
        return remark;
    }


    public void setBusiness(String business) {
        this.business = business;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCode_close(String code_close) {
        this.code_close = code_close;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setHard(String hard) {
        this.hard = hard;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Task() {
    }
    public Task(String task_number) {
        this.task_number = task_number;
    }

    public Task(String task_number, String remark){
        this.task_number=task_number;
        this.remark=remark;
    }
    public Task(String status, String hard, String category, String business,
                String code_close, String solution, String remark) {
        this.status = status;
        this.hard = hard;
        this.category = category;
        this.business = business;
        this.code_close = code_close;
        this.solution = solution;
        this.remark = remark;

    }


    public Task(String task_number, String product, String name, String surname,
                     String father_name, String mob, String email, String check,
                     String detail_problem, String date_time,
                     String status) {

        this.task_number = task_number;
        this.product = product;
        this.name = name;
        this.surname = surname;
        this.father_name = father_name;
        this.mob = mob;
        this.email = email;
        this.check = check;
        this.detail_problem = detail_problem;
        this.date_time = date_time;
        this.status = status;



    }
}
