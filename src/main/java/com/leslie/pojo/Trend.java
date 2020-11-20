package com.leslie.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("L_trend")
public class Trend {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("date")
    private String day;

    @TableField("sure_Cnt")
    private Integer sureCnt;

    @TableField("die_Cnt")
    private Integer dieCnt;

    @TableField("cure_Cnt")
    private Integer cureCnt;

    @TableField("doubt_Cnt")
    private Integer doubtCnt;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getSureCnt() {
        return sureCnt;
    }

    public void setSureCnt(Integer sureCnt) {
        this.sureCnt = sureCnt;
    }

    public Integer getDieCnt() {
        return dieCnt;
    }

    public void setDieCnt(Integer dieCnt) {
        this.dieCnt = dieCnt;
    }

    public Integer getCureCnt() {
        return cureCnt;
    }

    public void setCureCnt(Integer cureCnt) {
        this.cureCnt = cureCnt;
    }

    public Integer getDoubtCnt() {
        return doubtCnt;
    }

    public void setDoubtCnt(Integer doubtCnt) {
        this.doubtCnt = doubtCnt;
    }



    @Override
    public String toString() {
        return "Trend{" +
                "id=" + id +
                ", day=" + day +
                ", sureCnt=" + sureCnt +
                ", dieCnt=" + dieCnt +
                ", cureCnt=" + cureCnt +
                ", doubtCnt=" + doubtCnt +
                '}';
    }
}
