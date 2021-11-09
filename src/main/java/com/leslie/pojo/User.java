package com.leslie.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("L_user")
@Data
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String userName;

    private String loginName;

    private String loginPassword;

}
