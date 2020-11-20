package com.leslie.utils;

import com.leslie.cons.Const;

import java.util.HashMap;

public class AlibabaUtils {

    public static String requestAlibabaData(){

        HashMap<String, String> headers = new HashMap<>();

        headers.put("Authorization", "APPCODE "+ Const.aliAppCode);

        String remoteData = RemoteUtils.getRemoteData(Const.aliUrl, new HashMap<>(), headers);

        return remoteData;
    }
}
