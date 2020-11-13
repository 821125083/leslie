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

    @TableField("province_name")
    private String provinceName;

    @TableField("province_short_name")
    private String provinceShortName;

    @TableField("comments")
    @JSONField(alternateNames = "comment")
    private String comments;

    @TableField(exist = false)
    private List<City> cities;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getProvinceShortName() {
        return provinceShortName;
    }

    public void setProvinceShortName(String provinceShortName) {
        this.provinceShortName = provinceShortName;
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


    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "Province{" +
                "id=" + id +
                ", provinceName='" + provinceName + '\'' +
                ", provinceShortName='" + provinceShortName + '\'' +
                ", comments='" + comments + '\'' +
                ", cities=" + cities +
                '}';
    }
}
