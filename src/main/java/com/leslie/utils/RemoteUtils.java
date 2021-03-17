package com.leslie.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.leslie.pojo.Cov19CnRecord;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
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
import java.util.List;
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
        // 拼接请求参数
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
            LOGGER.error(e.getMessage());
        }finally {
            try {
                client.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return rep;
    }

    public static String getRemoteData(String url){
        return getRemoteData(url,new HashMap<String,String>(),new HashMap<String, String>());
    }


//    public static String request() throws Exception{
//        //创建请求 get、put、post、delete 对象
//        HttpRequestBase request = new HttpGet();
//        //封装请求数据 请求头请求体
//        request.setURI(url);
//        request.setHeader(header);
//        request.setConfig(config);
//        // 创建请求客户端对象
//        HttpClient httpClient = new HttpClient();
//        HttpEntity httpEntity = null;
//        // 客户点发送请
//        HttpResponse execute = httpClient.execute(request);
//        // 得到服务段返回数据
//        HttpEntity entity = execute.getEntity();
//        return EntityUtils.toString(entity);
//    }
    public String JSON() throws Exception{

        // JAVA 对象转化为字符串
//        String jsonStr = JSON.toJSONString(entity);
//        // json格式字符串转化为list集合
//        List<T> tlist = JSONArray.parseArray(jsonStr, T.class);
//        // json格式字符串转化为Java对象
//        T t = JSON.parseObject(jsonStr, T.class);


        return "";
    }

}
