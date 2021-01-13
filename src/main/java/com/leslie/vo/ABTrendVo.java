package com.leslie.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.leslie.pojo.Trend;

import java.util.List;

public class ABTrendVo {
    @JSONField(alternateNames = "childStatistic")
    private String name;

    private List<Trend> trend;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Trend> getTrend() {
        return trend;
    }

    public void setTrend(List<Trend> trend) {
        this.trend = trend;
    }

    @Override
    public String toString() {
        return "ABTrendVo{" +
                "name='" + name + '\'' +
                ", trend=" + trend +
                '}';
    }
}
