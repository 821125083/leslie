package com.leslie.controller;

import com.leslie.pojo.Students;
import com.leslie.service.StudentsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("students")
@CrossOrigin
public class StudentsController {

//    @Autowired
//    private Logger logger = LoggerFactory.getLogger(StudentsController.class);

    @Autowired
    private StudentsService studentsService;

    @RequestMapping("findAllStudents")
    @ResponseBody
    public List<Students> test(){
//        logger.info("查询所有的学生");
        List<Students> studentsList = studentsService.findAllStudents();
        studentsList.forEach(students -> {
            System.out.println(students.toString());
        });
        return studentsList;
    }
}
