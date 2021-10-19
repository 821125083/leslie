package com.leslie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leslie.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from L_user where login_name = #{username} and login_password = #{password}")
    boolean checkLogin(String username, String password);

    @Select("select * from L_user where login_name = #{username}")
    User selectUserByUserName(@Param("username") String username);
}
