package com.leslie.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

/**
 * 省
 */
@TableName("L_province")
public class Province {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @JSONField(alternateNames = "childStatistic")
    private String provinceName;


    @JSONField(alternateNames = "cityArray")
    private City cityList;

    public City getCityList() {
        return cityList;
    }

    public void setCityList(City cityList) {
        this.cityList = cityList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
    
}
