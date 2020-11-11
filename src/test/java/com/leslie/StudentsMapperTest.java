package com.leslie;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leslie.mapper.ClassesMapper;
import com.leslie.mapper.StudentsMapper;
import com.leslie.pojo.Classes;
import com.leslie.pojo.Cov19CnRecord;
import com.leslie.pojo.Students;
import com.leslie.utils.RemoteUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class StudentsMapperTest  {

    @Autowired
    private StudentsMapper studentsMapper;



    @Test
   public void test(){

        List<Students> students = studentsMapper.selectList(null);

        students.forEach(System.out::println);

    }



    @Test
    public void test2(){

        List<Students> studentAndClasses = studentsMapper.findStudentAndClasses();

        studentAndClasses.forEach(System.out::println);
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

    @Test
    public void test6() throws Exception {
        String url = "https://api.yonyoucloud.com/apis/dst/ncov/country";
        Map<String,String > headers = new HashMap<>();
        headers.put("apicode","4fa69c43e75f4779b6bc50177e361ee5");
        String remoteData = RemoteUtils.getRemoteData(url, new HashMap<>(), headers);
        Cov19CnRecord record = JSON.parseObject(JSON.parseObject(remoteData).getString("data"), Cov19CnRecord.class);
        System.out.println(record);
    }

}
