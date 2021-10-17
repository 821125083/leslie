package com.leslie.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leslie.cons.Const;
import com.leslie.pojo.Trend;
import com.leslie.vo.ABTrendVo;

import java.util.ArrayList;
import java.util.List;

public class ABUtils {


    public static JSONObject remoteObject(){
        String remoteData = RemoteUtils.getRemoteData(Const.ABURL);
        remoteData = remoteData.substring(19, remoteData.length()-1);
        return JSON.parseObject(remoteData);
    }

    //获得国外疫情趋势数据
    public static List<ABTrendVo> abroadIncTread(){
        JSONObject jsonObject = remoteObject();
        Object intTrend = jsonObject.get("intTrend");
        return JSON.parseArray(intTrend.toString(), ABTrendVo.class);
    }
}
