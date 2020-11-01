package com.leslie.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import org.apache.ibatis.annotations.Result;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Students {

    @TableId(type = IdType.AUTO)
    private int id;

    private int cid;

    private String name;

    private int age;

    private int gender;

    @TableField(exist = false)
    private Classes classes;


}
