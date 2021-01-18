package com.leslie.cons;

/**
 * 存放常量
 */
public class Const {

    //apiCode
    @Deprecated
    public final static String apiCode = "4fa69c43e75f4779b6bc50177e361ee5";

    //疫情统计全国url
    @Deprecated
    public final static String CNUrl = "https://api.yonyoucloud.com/apis/dst/ncov/country";

    //疫情统计地图url
    @Deprecated
    public final static String MAPUrl = "https://api.yonyoucloud.com/apis/dst/ncov/spreadQuery";

    //疫情统计全球url
    @Deprecated
    public final static String ABUrl = "https://api.yonyoucloud.com/apis/dst/ncov/wholeworld";

    //疫情统计播报url
    public final static String newsUrl = "https://cdn.mdeer.com/contentdtos.js";

    public final static String ABURL = "https://cdn.mdeer.com/data/yqstaticdata.js";

    //阿里api接口
    public final static String aliUrl = "https://ncovdata.market.alicloudapi.com/ncov/cityDiseaseInfoWithTrend";

    //aliappcode
    public final static String aliAppCode = "1d39733653f948cf89e1066fa80f1c25";

    //163 api接口
    public final static String Api163 = "https://c.m.163.com/ug/api/wuhan/app/data/list-total";


    public final static String weatherApi = "http://t.weather.itboy.net/api/weather/city/";

    public final static Integer GUANGZHOUWEATHERCODE = 101280101;

    public final static String ES_NEWS_INDEX = "news_index";

    public final static Integer DEFAULT_PAGE_SIZE = 10;

    /**
     * 丁香园 全球疫情 api 接口 截止 2020.3.19
     */
    @Deprecated
    public final static String DXY_API = "https://lab.isaaclin.cn/nCoV/api/area";
}
