package com.leslie.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.leslie.cons.Const;
import com.leslie.exception.AliInterfaceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class AlibabaUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlibabaUtils.class);

    public static String requestAlibabaData()  {

        HashMap<String, String> headers = new HashMap<>();

        headers.put("Authorization", "APPCODE "+ Const.aliAppCode);

        String remoteData = RemoteUtils.getRemoteData(Const.aliUrl, new HashMap<>(), headers);

        return remoteData;
    }

    public static JSONObject requestAlibabaJsonObject() throws Exception{
        JSONObject jsonObject = null;

        jsonObject = JSON.parseObject(requestAlibabaData());

        return jsonObject;
    }
}
