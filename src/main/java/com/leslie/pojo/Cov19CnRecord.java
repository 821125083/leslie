package com.leslie.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("L_cov19cn_record")
public class Cov19CnRecord {
//    id
    @TableId(type = IdType.AUTO)
    private Integer id;
//    更新时间
    private Date updateTime;
//    确诊总数
    private Integer confirmedCount;
//    较昨日新增总数
    private Integer confirmedAdd;
//    当日确诊总数
    private Integer currentConfirmedCount;

    private Integer currentConfirmedAdd;
//    疑似病例
    private Integer suspectedCount;

    private Integer suspectedAdd;
//    重症总数
    private Integer severecasesCount;

    private Integer severecasesAdd;
//    治愈总数
    private Integer curedCount;

    private Integer curedAdd;
//    死亡总数
    private Integer deadCount;

    @TableField("dead_add")
    private Integer deathAdd;
//    来源
    private String sourceDesc;
//    描述
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date date) {
        this.updateTime = date;
    }

    public Integer getConfirmedCount() {
        return confirmedCount;
    }

    public void setConfirmedCount(Integer confirmedCount) {
        this.confirmedCount = confirmedCount;
    }

    public Integer getConfirmedAdd() {
        return confirmedAdd;
    }

    public void setConfirmedAdd(Integer confirmedAdd) {
        this.confirmedAdd = confirmedAdd;
    }

    public Integer getCurrentConfirmedCount() {
        return currentConfirmedCount;
    }

    public void setCurrentConfirmedCount(Integer currentConfirmedCount) {
        this.currentConfirmedCount = currentConfirmedCount;
    }

    public Integer getCurrentConfirmedAdd() {
        return currentConfirmedAdd;
    }

    public void setCurrentConfirmedAdd(Integer currentConfirmedAdd) {
        this.currentConfirmedAdd = currentConfirmedAdd;
    }

    public Integer getSuspectedCount() {
        return suspectedCount;
    }

    public void setSuspectedCount(Integer suspectedCount) {
        this.suspectedCount = suspectedCount;
    }

    public Integer getSuspectedAdd() {
        return suspectedAdd;
    }

    public void setSuspectedAdd(Integer suspectedAdd) {
        this.suspectedAdd = suspectedAdd;
    }

    public Integer getSeverecasesCount() {
        return severecasesCount;
    }

    public void setSeverecasesCount(Integer severecasesCount) {
        this.severecasesCount = severecasesCount;
    }

    public Integer getSeverecasesAdd() {
        return severecasesAdd;
    }

    public void setSeverecasesAdd(Integer severecasesAdd) {
        this.severecasesAdd = severecasesAdd;
    }

    public Integer getCuredCount() {
        return curedCount;
    }

    public void setCuredCount(Integer curedCount) {
        this.curedCount = curedCount;
    }

    public Integer getCuredAdd() {
        return curedAdd;
    }

    public void setCuredAdd(Integer curedAdd) {
        this.curedAdd = curedAdd;
    }

    public Integer getDeadCount() {
        return deadCount;
    }

    public void setDeadCount(Integer deadCount) {
        this.deadCount = deadCount;
    }

    public Integer getDeathAdd() {
        return deathAdd;
    }

    public void setDeathAdd(Integer deathAdd) {
        this.deathAdd = deathAdd;
    }

    public String getSourceDesc() {
        return sourceDesc;
    }

    public void setSourceDesc(String sourceDesc) {
        this.sourceDesc = sourceDesc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Cov19CnRecord{" +
                "id=" + id +
                ", updateTime=" + updateTime +
                ", confirmedCount=" + confirmedCount +
                ", confirmedAdd=" + confirmedAdd +
                ", currentConfirmedCount=" + currentConfirmedCount +
                ", currentConfirmedAdd=" + currentConfirmedAdd +
                ", suspectedCount=" + suspectedCount +
                ", suspectedAdd=" + suspectedAdd +
                ", severecasesCount=" + severecasesCount +
                ", severecasesAdd=" + severecasesAdd +
                ", curedCount=" + curedCount +
                ", curedAdd=" + curedAdd +
                ", deadCount=" + deadCount +
                ", deathAdd=" + deathAdd +
                ", sourceDesc='" + sourceDesc + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
