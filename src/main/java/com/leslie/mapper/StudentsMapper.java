package com.leslie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leslie.pojo.Classes;
import com.leslie.pojo.Students;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public interface StudentsMapper extends BaseMapper<Students> {


    @Results(id="selectById",value = {
            @Result(property = "id",column = "id",id = true),
            /*@Result(property = "cid",column = "cid"),*/
            @Result(property = "name",column = "name"),
            @Result(property = "age",column = "age"),
            @Result(property = "gender",column = "gender"),
            @Result(property = "classes",javaType = Classes.class,
                    one=@One(select = "com.leslie.mapper.ClassesMapper.selectById"),column = "cid"),
    })
    @Select("SELECT * from students s, classes c where s.cid = c.id;")
//    CopyOnWriteArrayList<Students> findStudentAndClasses();
    ArrayList<Students> findStudentAndClasses();




}
