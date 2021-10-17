package com.leslie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leslie.pojo.Province;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProvinceMapper extends BaseMapper<Province> {

    @Select("select * from L_province where id = #{id} union select * from L_province where province_name = #{name}")
    ArrayList<Province> queryProvinceByNameAndId(@Param("id") Integer id,@Param("name") String name);
}
