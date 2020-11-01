package com.leslie;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leslie.mapper.ClassesMapper;
import com.leslie.mapper.StudentsMapper;
import com.leslie.pojo.Classes;
import com.leslie.pojo.Students;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class StudentsMapperTest  {

    @Autowired
    private StudentsMapper studentsMapper;

    @Autowired
    private ClassesMapper classesMapper;

    @Test
   public void test(){

        List<Students> students = studentsMapper.selectList(null);

        students.forEach(System.out::println);

    }

    @Test
    public void test1(){

        QueryWrapper<Students> wrapper = new QueryWrapper<>();

        Classes classes = classesMapper.selectById(1);

        wrapper.eq(false, "classes", classes);

        List<Students> students = studentsMapper.selectList(wrapper);

        System.out.println(students);
    }

    @Test
    public void test2(){

        List<Students> studentAndClasses = studentsMapper.findStudentAndClasses();

        studentAndClasses.forEach(System.out::println);
    }

    @Test
    public void test3(){
        List<Classes> classesAndStudents = classesMapper.findClassesAndStudents();

        classesAndStudents.forEach(System.out::println);

    }

    @Test
    public void test4(){

        QueryWrapper<Students> wrapper = new QueryWrapper<>();

        wrapper.eq("name", "leslie");

        List<Students> students = studentsMapper.selectList(wrapper);

        students.forEach(System.out::println);
    }

    @Test
    public void test5(){


        QueryWrapper<Students> wrapper  =  new QueryWrapper<>();

        wrapper.ge("age", 15);

        Integer integer = studentsMapper.selectCount(wrapper);

        System.out.println(integer);

    }

}
