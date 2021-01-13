package com.leslie.pojo;


import java.time.LocalDate;

public class Weather {

    //日期
    private LocalDate ymd;

    //日
    private String date;

    //最高气温
    private String high;

    //日出时间
    private String sunRise;

    //风向
    private String fx;

    //星期几
    private String week;

    //最低温
    private String low;

    //风力
    private String fl;

    //日落时间
    private String sunSet;

    //湿度
    private String aqi;

    //天气类型
    private String type;

    //注意事项
    private String notice;

    public LocalDate getYmd() {
        return ymd;
    }

    public void setYmd(LocalDate ymd) {
        this.ymd = ymd;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getSunRise() {
        return sunRise;
    }

    public void setSunRise(String sunRise) {
        this.sunRise = sunRise;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getSunSet() {
        return sunSet;
    }

    public void setSunSet(String sunSet) {
        this.sunSet = sunSet;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "ymd=" + ymd +
                ", date='" + date + '\'' +
                ", high='" + high + '\'' +
                ", sunRise='" + sunRise + '\'' +
                ", fx='" + fx + '\'' +
                ", week='" + week + '\'' +
                ", low='" + low + '\'' +
                ", fl='" + fl + '\'' +
                ", sunSet='" + sunSet + '\'' +
                ", aqi='" + aqi + '\'' +
                ", type='" + type + '\'' +
                ", notice='" + notice + '\'' +
                '}';
    }
}
