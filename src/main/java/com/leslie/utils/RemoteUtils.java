package com.leslie.utils;

import com.alibaba.fastjson.JSON;
import com.leslie.pojo.Cov19CnRecord;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * 调用远程接口的工具类
 */
public class RemoteUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteUtils.class);


    /**
     * 远程请求发送 get api
     * @param url
     * @param params 请求拼接参数
     * @param headers 请求头
     * @return
     *
     */
    public static String getRemoteData(String url, Map<String,String> params ,Map<String,String > headers) {
        //拼接请求参数
        if (null != params && params.size() > 0){
            String param = "?";
            for (Map.Entry<String, String> entry : params.entrySet()) {
                param += param + entry.getKey() + "=" + entry.getValue() + "&";
            }
            url +=  param;
        }

        //创建get请求
        HttpRequestBase request = new HttpGet();
        //设置请求路径
        request.setURI(URI.create(url));
        //设置请求头
        if (headers.size() > 0){
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request.setHeader(entry.getKey(),entry.getValue());
            }
        }

        //设置请求的基本配置超时时间
        RequestConfig config = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(10000).build();
        request.setConfig(config);
//        创建请求客户端
        CloseableHttpClient client = HttpClients.createDefault();
        HttpResponse response = null;
        LOGGER.info("execute get request url:{}",url);
        String rep ="";
        try {
//        执行get请求
            response = client.execute(request);
            //获得返回的数据
            rep = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rep;
    }

    public static String getRemoteData(String url){
        return getRemoteData(url,new HashMap<String,String>(),new HashMap<String, String>());
    }

}
