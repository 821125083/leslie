package com.leslie.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

public class JsonUtils {


    public static <T> T getObject(Class<T> clazz,String ObjectString){
        return (T)JSON.parseObject(ObjectString,clazz.getClass());
    }

    public static JSONObject toJsonObject(String string){
        if (null!=string && !StringUtils.equals("",string)) {
            return JSON.parseObject(string);
        }
        else {
            return null;
        }
    }



}
