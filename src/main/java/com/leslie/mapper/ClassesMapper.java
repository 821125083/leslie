package com.leslie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leslie.pojo.Classes;
import com.leslie.pojo.Students;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface ClassesMapper extends BaseMapper<Classes> {

    @Results(id = "com.leslie.mapper.ClassesMapper.selectById",value = {
            @Result(property = "id",column = "id",id = true),
            @Result(property = "grade",column = "grade"),
            @Result(property = "class_num",column = "class_num"),
            @Result(property = "students",column = "id",javaType = List.class,
                    many = @Many(select = "selectClassesByGroupStudents"))
    })
//    @Select("SELECT * from students s, classes c where s.cid = c.id;")
    @Select("select * from classes")
    List<Classes> findClassesAndStudents();



    @Select("select * from classes c left JOIN  students s on c.id = s.cid where s.cid = #{cid}")
    List<Students> selectClassesByGroupStudents(@Param("cid") int cid);


}
